package com.alanturing.society.feature.acceso.data.remote

import com.alanturing.society.feature.acceso.model.Sesion

// Devuelve la sesión (Result.success) o la excepción del error (Result.failure)
interface AuthDataSource {
    suspend fun registro(email: String, contrasena: String, terminosVersion: String): Result<Sesion>
    suspend fun acceso(email: String, contrasena: String): Result<Sesion>
}