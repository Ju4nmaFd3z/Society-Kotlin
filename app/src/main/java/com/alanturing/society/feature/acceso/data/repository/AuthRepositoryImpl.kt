package com.alanturing.society.feature.acceso.data.repository

import com.alanturing.society.feature.acceso.data.local.SesionLocalDataSource
import com.alanturing.society.feature.acceso.data.remote.AuthDataSource
import com.alanturing.society.feature.acceso.model.Sesion
import javax.inject.Inject
import kotlinx.coroutines.flow.first

// Versión de los términos que acepta el usuario al registrarse (la del contrato de la API)
private const val TERMINOS_VERSION = "2026-11"

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthDataSource,
    private val localDataSource: SesionLocalDataSource
) : AuthRepository {

    // Crea la cuenta y, si la API acepta, guarda la sesión
    override suspend fun registro(email: String, contrasena: String): Result<Unit> {
        val resultado = remoteDataSource.registro(email.trim(), contrasena, TERMINOS_VERSION)
        return guardarSiCorrecto(resultado)
    }

    // Inicia sesión y, si la API acepta, guarda la sesión
    override suspend fun acceso(email: String, contrasena: String): Result<Unit> {
        val resultado = remoteDataSource.acceso(email.trim(), contrasena)
        return guardarSiCorrecto(resultado)
    }

    override suspend fun haySesion(): Boolean = localDataSource.sesion.first() != null

    override suspend fun cerrarSesion() = localDataSource.borrar()

    // Si la API devolvió la sesión, la guarda en el móvil; si no, deja pasar la excepción
    private suspend fun guardarSiCorrecto(resultado: Result<Sesion>): Result<Unit> =
        resultado.map { sesion -> localDataSource.guardar(sesion) }
}
