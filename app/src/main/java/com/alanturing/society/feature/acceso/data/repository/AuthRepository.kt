package com.alanturing.society.feature.acceso.data.repository

interface AuthRepository {
    // Result.success si la API acepta (y la sesión queda guardada); Result.failure con la excepción si no
    suspend fun registro(email: String, contrasena: String): Result<Unit>
    suspend fun acceso(email: String, contrasena: String): Result<Unit>

    // true si hay tokens guardados en el móvil
    suspend fun haySesion(): Boolean

    // Borra los tokens guardados
    suspend fun cerrarSesion()
}