package com.alanturing.society.feature.acceso.data.remote

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