package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.data.remote.CreateResult
import com.alanturing.society.feature.arranque.data.remote.RespuestaMio
import com.alanturing.society.feature.arranque.model.ManualRestaurant

interface RestauranteRepository {
    suspend fun miRestaurante(): RespuestaMio

    suspend fun createRestaurant(googlePlaceId: String): CreateResult

    suspend fun createManualRestaurant(restaurant: ManualRestaurant): CreateResult

    // Devuelve el analisisId, o null si no se pudo lanzar
    suspend fun launchAnalysis(restauranteId: String): String?
}
