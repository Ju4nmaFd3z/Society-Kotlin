package com.alanturing.society.feature.acceso.data.remote

import com.alanturing.society.core.network.SocietyApi
import com.alanturing.society.feature.acceso.data.remote.exception.CredentialsNotValidException
import com.alanturing.society.feature.acceso.data.remote.exception.DataNotValidException
import com.alanturing.society.feature.acceso.data.remote.exception.EmailAlreadyInUseException
import com.alanturing.society.feature.acceso.data.remote.exception.NoConnectionException
import com.alanturing.society.feature.acceso.data.remote.exception.ServerErrorException
import com.alanturing.society.feature.acceso.data.remote.exception.TooManyAttemptsException
import com.alanturing.society.feature.acceso.model.Sesion
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
    ): Result<Sesion> = llamar {
        api.registro(RegistroPeticion(email, contrasena, terminosVersion))
    }

    override suspend fun acceso(email: String, contrasena: String): Result<Sesion> = llamar {
        api.acceso(AccesoPeticion(email, contrasena))
    }

    // Misma forma de tratar los errores en las dos llamadas
    private suspend fun llamar(peticion: suspend () -> Response<SesionResponse>): Result<Sesion> {
        return try {
            val respuesta = peticion()
            val cuerpo = respuesta.body()
            if (respuesta.isSuccessful && cuerpo != null) {
                Result.success(cuerpo.toModel())
            } else {
                Result.failure(traducirError(respuesta))
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            Result.failure(NoConnectionException())
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            Result.failure(ServerErrorException())
        }
    }

    // Lee el "code" del cuerpo de error y lo pasa a su excepción (si no se puede, usa el código HTTP)
    private fun traducirError(respuesta: Response<*>): Exception {
        val codigo = try {
            Gson().fromJson(respuesta.errorBody()?.string(), ErrorResponse::class.java)?.error?.code
        } catch (e: JsonParseException) {
            null
        }
        return when (codigo) {
            "VALIDACION" -> DataNotValidException()
            "CREDENCIALES_INCORRECTAS" -> CredentialsNotValidException()
            "DUPLICADO" -> EmailAlreadyInUseException()
            "LIMITE_ALCANZADO" -> TooManyAttemptsException()
            else -> when (respuesta.code()) {
                400 -> DataNotValidException()
                401 -> CredentialsNotValidException()
                409 -> EmailAlreadyInUseException()
                429 -> TooManyAttemptsException()
                else -> ServerErrorException()
            }
        }
    }
}