package com.alanturing.society.feature.acceso.data.remote

import com.alanturing.society.feature.acceso.model.ErrorAcceso
import com.alanturing.society.feature.acceso.model.Sesion

// Lo que devuelve el DataSource: la sesión o el error
sealed interface RespuestaAuth {
    data class Ok(val sesion: Sesion) : RespuestaAuth
    data class Error(val error: ErrorAcceso) : RespuestaAuth
}

interface AuthDataSource {
    suspend fun registro(email: String, contrasena: String, terminosVersion: String): RespuestaAuth
    suspend fun acceso(email: String, contrasena: String): RespuestaAuth
}