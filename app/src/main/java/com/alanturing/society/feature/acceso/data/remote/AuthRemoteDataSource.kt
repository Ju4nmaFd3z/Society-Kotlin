package com.alanturing.society.feature.acceso.data.remote

import com.alanturing.society.core.network.SocietyApi
import com.alanturing.society.feature.acceso.model.ErrorAcceso
import com.google.gson.Gson
import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject
import retrofit2.Response

class AuthRemoteDataSource @Inject constructor(
    private val api: SocietyApi
) : AuthDataSource {

    override suspend fun registro(
        email: String,
        contrasena: String,
        terminosVersion: String
    ): RespuestaAuth = llamar {
        api.registro(RegistroPeticion(email, contrasena, terminosVersion))
    }

    override suspend fun acceso(email: String, contrasena: String): RespuestaAuth = llamar {
        api.acceso(AccesoPeticion(email, contrasena))
    }

    // Misma forma de tratar los errores en las dos llamadas
    private suspend fun llamar(peticion: suspend () -> Response<SesionResponse>): RespuestaAuth {
        return try {
            val respuesta = peticion()
            val cuerpo = respuesta.body()
            if (respuesta.isSuccessful && cuerpo != null) {
                RespuestaAuth.Ok(cuerpo.toModel())
            } else {
                RespuestaAuth.Error(traducirError(respuesta))
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            RespuestaAuth.Error(ErrorAcceso.SIN_CONEXION)
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            RespuestaAuth.Error(ErrorAcceso.SERVIDOR)
        }
    }

    // Lee el "code" del cuerpo de error y lo pasa a ErrorAcceso (si no se puede, usa el código HTTP)
    private fun traducirError(respuesta: Response<*>): ErrorAcceso {
        val codigo = try {
            Gson().fromJson(respuesta.errorBody()?.string(), ErrorResponse::class.java)?.error?.code
        } catch (e: JsonParseException) {
            null
        }
        return when (codigo) {
            "VALIDACION" -> ErrorAcceso.DATOS_INVALIDOS
            "CREDENCIALES_INCORRECTAS" -> ErrorAcceso.CREDENCIALES_INCORRECTAS
            "DUPLICADO" -> ErrorAcceso.EMAIL_DUPLICADO
            "LIMITE_ALCANZADO" -> ErrorAcceso.DEMASIADOS_INTENTOS
            else -> when (respuesta.code()) {
                400 -> ErrorAcceso.DATOS_INVALIDOS
                401 -> ErrorAcceso.CREDENCIALES_INCORRECTAS
                409 -> ErrorAcceso.EMAIL_DUPLICADO
                429 -> ErrorAcceso.DEMASIADOS_INTENTOS
                else -> ErrorAcceso.SERVIDOR
            }
        }
    }
}