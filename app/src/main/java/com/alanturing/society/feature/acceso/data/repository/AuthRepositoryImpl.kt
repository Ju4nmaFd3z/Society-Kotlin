package com.alanturing.society.feature.acceso.data.repository

import com.alanturing.society.feature.acceso.data.local.SesionLocalDataSource
import com.alanturing.society.feature.acceso.data.remote.AuthDataSource
import com.alanturing.society.feature.acceso.data.remote.RespuestaAuth
import com.alanturing.society.feature.acceso.model.ResultadoAcceso
import javax.inject.Inject
import kotlinx.coroutines.flow.first

// TODO: Versión de los términos que acepta el usuario al registrarse, cambiar por la nuesta
private const val TERMINOS_VERSION = "1.0"

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource,
    private val localDataSource: SesionLocalDataSource
) : AuthRepository {

    // Crea la cuenta y, si la API acepta, guarda la sesión
    override suspend fun registro(email: String, contrasena: String): ResultadoAcceso {
        val respuesta = remoteDataSource.registro(email.trim(), contrasena, TERMINOS_VERSION)
        return guardarSiCorrecto(respuesta)
    }

    // Inicia sesión y, si la API acepta, guarda la sesión
    override suspend fun acceso(email: String, contrasena: String): ResultadoAcceso {
        val respuesta = remoteDataSource.acceso(email.trim(), contrasena)
        return guardarSiCorrecto(respuesta)
    }

    override suspend fun haySesion(): Boolean = localDataSource.sesion.first() != null

    override suspend fun cerrarSesion() = localDataSource.borrar()

    // Si la API devolvió la sesión, la guarda en el móvil; si no, devuelve el error
    private suspend fun guardarSiCorrecto(respuesta: RespuestaAuth): ResultadoAcceso {
        return when (respuesta) {
            is RespuestaAuth.Ok -> {
                localDataSource.guardar(respuesta.sesion)
                ResultadoAcceso.Correcto
            }
            is RespuestaAuth.Error -> ResultadoAcceso.Fallo(respuesta.error)
        }
    }
}