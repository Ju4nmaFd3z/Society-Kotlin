package com.alanturing.society.feature.acceso.data.repository

import com.alanturing.society.feature.acceso.model.ResultadoAcceso

interface AuthRepository {
    suspend fun registro(email: String, contrasena: String): ResultadoAcceso
    suspend fun acceso(email: String, contrasena: String): ResultadoAcceso
}