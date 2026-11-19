package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.feature.arranque.model.MiRestaurante

// Lo que devuelve el DataSource: el local, o el motivo por el que no se pudo
sealed interface RespuestaMio {
    data class Ok(val miRestaurante: MiRestaurante) : RespuestaMio
    data object SesionCaducada : RespuestaMio // 401 incluso tras intentar renovar
    data object Error : RespuestaMio          // sin conexión, 5xx o respuesta ilegible
}

interface RestauranteDataSource {
    suspend fun miRestaurante(): RespuestaMio

    // Devuelve el id del análisis lanzado, o null si no se pudo
    suspend fun lanzarAnalisis(restauranteId: String): String?
}
