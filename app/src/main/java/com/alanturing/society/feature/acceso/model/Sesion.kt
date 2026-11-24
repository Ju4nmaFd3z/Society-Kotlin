package com.alanturing.society.feature.acceso.model

// Sesión que devuelve la API
data class Sesion(
    val accessToken: String,
    val refreshToken: String,
    val expiraEn: Long // segundos
)
