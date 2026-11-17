package com.alanturing.society.feature.acceso.data.remote

import com.alanturing.society.feature.acceso.model.Sesion

// Cuerpo de POST /auth/acceso (el contrato usa "contrasena", sin ñ)
data class AccesoPeticion(
    val email: String,
    val contrasena: String
)

// Cuerpo de POST /auth/registro. terminosVersion es obligatorio: la API guarda versión y fecha en perfil_usuario
data class RegistroPeticion(
    val email: String,
    val contrasena: String,
    val terminosVersion: String
)

// Respuesta correcta de /auth/registro (201) y /auth/acceso (200)
data class SesionResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiraEn: Long // segundos que le quedan al accessToken
)

// Respuesta de error de la API: {"error": {"code", "message", "retryable"}}
data class ErrorResponse(val error: ErrorCuerpo?)
data class ErrorCuerpo(val code: String?, val message: String?, val retryable: Boolean?)

// Pasa la respuesta de la API a la clase que usa el resto de la app
fun SesionResponse.toModel(): Sesion {
    return Sesion(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiraEn = expiraEn
    )
}