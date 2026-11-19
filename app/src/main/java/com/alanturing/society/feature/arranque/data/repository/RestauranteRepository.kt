package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.data.remote.RespuestaMio

interface RestauranteRepository {
    suspend fun miRestaurante(): RespuestaMio
}
