package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.feature.arranque.model.MiRestaurante

interface RestauranteDataSource {
    // El local del usuario (Result.success) o la excepción del fallo (Result.failure)
    suspend fun miRestaurante(): Result<MiRestaurante>

    // Devuelve el id del análisis lanzado, o la excepción del fallo
    suspend fun lanzarAnalisis(restauranteId: String): Result<String>
}
