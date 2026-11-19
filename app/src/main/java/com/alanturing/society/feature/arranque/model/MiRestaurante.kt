package com.alanturing.society.feature.arranque.model

// En qué punto del alta está el usuario (los valores los define la API)
enum class AltaEstado { SIN_LOCAL, CREADO, ANALIZANDO, PENDIENTE_CONFIRMAR, COMPLETADA }

// Lo que la app necesita saber de GET /restaurantes/mio
data class MiRestaurante(
    val altaEstado: AltaEstado,
    val restauranteId: String?,     // null si SIN_LOCAL
    val analisisEnCursoId: String?  // solo con ANALIZANDO
)
