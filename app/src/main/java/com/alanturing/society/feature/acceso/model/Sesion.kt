package com.alanturing.society.feature.acceso.model

// Sesión que devuelve la API
data class Sesion(
    val accessToken: String,
    val refreshToken: String,
    val expiraEn: Long // segundos
    )

// Errores. Cada valor corresponde a un código de error de la API
enum class ErrorAcceso {
    DATOS_INVALIDOS,          // 400 VALIDACION
    CREDENCIALES_INCORRECTAS, // 401
    EMAIL_DUPLICADO,          // 409 DUPLICADO
    DEMASIADOS_INTENTOS,      // 429 LIMITE_ALCANZADO
    SIN_CONEXION,             // no se llegó al servidor
    SERVIDOR                  // 503, 500 o respuesta ilegible
    }

// Resultado de registro o login
sealed interface ResultadoAcceso {
    data object Correcto : ResultadoAcceso
    data class Fallo(val error: ErrorAcceso) : ResultadoAcceso
    }