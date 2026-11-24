package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.data.remote.CreateResult
import com.alanturing.society.feature.arranque.data.remote.RespuestaMio

interface RestauranteRepository {
    suspend fun miRestaurante(): RespuestaMio

    suspend fun createRestaurant(googlePlaceId: String): CreateResult

    // Devuelve el analisisId, o null si no se pudo lanzar
    suspend fun launchAnalysis(restauranteId: String): String?
}
