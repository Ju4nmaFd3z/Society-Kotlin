package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.model.MiRestaurante

interface RestauranteRepository {
    suspend fun miRestaurante(): Result<MiRestaurante>
}
