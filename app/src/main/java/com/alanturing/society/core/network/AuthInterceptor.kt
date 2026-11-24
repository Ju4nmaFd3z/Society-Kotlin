package com.alanturing.society.core.network

import com.alanturing.society.feature.acceso.data.local.SesionLocalDataSource
import com.alanturing.society.feature.acceso.data.remote.RefrescoPeticion
import com.alanturing.society.feature.acceso.model.Sesion
import dagger.Lazy
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val sesionLocal: SesionLocalDataSource,
    private val api: Lazy<SocietyApi> // Lazy: se crea al usarla, así no hay ciclo con el cliente
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val peticion = chain.request()

        // Acceso, registro y refresco no llevan token (su 401 significa credenciales incorrectas)
        if ("/auth/" in peticion.url.encodedPath) return chain.proceed(peticion)

        val sesion = runBlocking { sesionLocal.sesion.first() } ?: return chain.proceed(peticion)
        val respuesta = chain.proceed(peticion.conToken(sesion.accessToken))

        if (respuesta.code != 401) return respuesta

        //REfresco
        // 401: se renueva una vez. Si falla, se devuelve el 401 y ya
        val nueva = runBlocking {
            api.get().refresco(RefrescoPeticion(sesion.refreshToken)).body()
        } ?: return respuesta

        runBlocking {
            sesionLocal.guardar(Sesion(nueva.accessToken, nueva.refreshToken, nueva.expiraEn))
        }

        respuesta.close()

        return chain.proceed(peticion.conToken(nueva.accessToken))
    }

    private fun Request.conToken(token: String) =
        newBuilder().header("Authorization", "Bearer $token").build()
}