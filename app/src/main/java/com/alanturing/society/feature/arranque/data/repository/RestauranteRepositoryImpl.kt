package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.data.remote.RespuestaMio
import com.alanturing.society.feature.arranque.data.remote.RestauranteDataSource
import javax.inject.Inject

class RestauranteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestauranteDataSource
) : RestauranteRepository {

    override suspend fun miRestaurante(): RespuestaMio = remoteDataSource.miRestaurante()
}
