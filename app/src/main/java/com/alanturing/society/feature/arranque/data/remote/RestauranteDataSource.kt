package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.feature.arranque.model.MiRestaurante

// Lo que devuelve el DataSource: el local, o el motivo por el que no se pudo
sealed interface RespuestaMio {
    data class Ok(val miRestaurante: MiRestaurante) : RespuestaMio
    data object SesionCaducada : RespuestaMio // 401 incluso tras intentar renovar
    data object Error : RespuestaMio          // sin conexión, 5xx o respuesta ilegible
}

// Resultado de dar de alta el local
sealed interface CreateResult {
    data class Created(val restauranteId: String) : CreateResult
    data object Duplicate : CreateResult      // 409: ya es de otra cuenta o el usuario ya tiene local
    data object SessionExpired : CreateResult
    data object Error : CreateResult
}

interface RestauranteDataSource {
    suspend fun miRestaurante(): RespuestaMio

    // Alta desde un resultado de la búsqueda, con su placeId de Google
    suspend fun createRestaurant(googlePlaceId: String): CreateResult

    // Devuelve el id del análisis lanzado, o null si no se pudo
    suspend fun lanzarAnalisis(restauranteId: String): String?
}
