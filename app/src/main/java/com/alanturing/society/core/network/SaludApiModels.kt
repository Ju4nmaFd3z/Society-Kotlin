package com.alanturing.society.core.network

// Respuesta de GET /salud tal y como la envía la API
data class SaludResponse(
    val ok: Boolean
)

// Pasa la respuesta de la API a la clase que usa el resto de la app
fun SaludResponse.toModel(): Salud {
    return Salud(ok = this.ok)
}
