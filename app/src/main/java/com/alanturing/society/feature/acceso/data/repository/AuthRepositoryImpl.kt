package com.alanturing.society.feature.acceso.data.repository

import com.alanturing.society.feature.acceso.data.remote.AuthDataSource
import com.alanturing.society.feature.acceso.data.remote.RespuestaAuth
import com.alanturing.society.feature.acceso.model.ResultadoAcceso
import javax.inject.Inject

// Versión de los términos que acepta el usuario al registrarse (la API la guarda en perfil_usuario)
private const val TERMINOS_VERSION = "1.0"

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun registro(email: String, contrasena: String): ResultadoAcceso {
        val respuesta = remoteDataSource.registro(email.trim(), contrasena, TERMINOS_VERSION)
        return aResultado(respuesta)
    }

    override suspend fun acceso(email: String, contrasena: String): ResultadoAcceso {
        val respuesta = remoteDataSource.acceso(email.trim(), contrasena)
        return aResultado(respuesta)
    }

    // De momento solo traduce. Más adelante aquí se guardarán los tokens
    private fun aResultado(respuesta: RespuestaAuth): ResultadoAcceso {
        return when (respuesta) {
            is RespuestaAuth.Ok -> ResultadoAcceso.Correcto // TODO: guardar la sesión en TokenStore
            is RespuestaAuth.Error -> ResultadoAcceso.Fallo(respuesta.error)
        }
    }
}