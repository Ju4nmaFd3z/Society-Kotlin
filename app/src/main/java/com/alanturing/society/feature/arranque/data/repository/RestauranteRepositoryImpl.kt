package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.arranque.data.remote.CreateResult
import com.alanturing.society.feature.arranque.data.remote.RespuestaMio
import com.alanturing.society.feature.arranque.model.AltaEstado
import com.alanturing.society.feature.arranque.data.remote.RestauranteDataSource
import javax.inject.Inject

class RestauranteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestauranteDataSource
) : RestauranteRepository {

    override suspend fun miRestaurante(): RespuestaMio {
        val respuesta = remoteDataSource.miRestaurante()
        if (respuesta !is RespuestaMio.Ok || respuesta.miRestaurante.altaEstado != AltaEstado.CREADO) return respuesta

        //Lanza el analisis y se trata como ANALIZANDO
        val id = respuesta.miRestaurante.restauranteId ?: return RespuestaMio.Error
        val analisisId = remoteDataSource.lanzarAnalisis(id) ?: return RespuestaMio.Error
        return RespuestaMio.Ok(
            respuesta.miRestaurante.copy(altaEstado = AltaEstado.ANALIZANDO, analisisEnCursoId = analisisId)
        )
    }

    override suspend fun createRestaurant(googlePlaceId: String): CreateResult =
        remoteDataSource.createRestaurant(googlePlaceId)

    override suspend fun launchAnalysis(restauranteId: String): String? =
        remoteDataSource.lanzarAnalisis(restauranteId)
}
