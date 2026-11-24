package com.alanturing.society.feature.arranque.data.repository

import com.alanturing.society.feature.acceso.data.remote.exception.ServerErrorException
import com.alanturing.society.feature.arranque.model.AltaEstado
import com.alanturing.society.feature.arranque.data.remote.RestauranteDataSource
import com.alanturing.society.feature.arranque.model.MiRestaurante
import javax.inject.Inject

class RestauranteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestauranteDataSource
) : RestauranteRepository {

    override suspend fun miRestaurante(): Result<MiRestaurante> {
        // Si falla, se devuelve la excepción tal cual
        val miRestaurante = remoteDataSource.miRestaurante().getOrElse { return Result.failure(it) }
        if (miRestaurante.altaEstado != AltaEstado.CREADO) return Result.success(miRestaurante)

        //Lanza el analisis y se trata como ANALIZANDO
        val id = miRestaurante.restauranteId ?: return Result.failure(ServerErrorException())
        return remoteDataSource.lanzarAnalisis(id).map { analisisId ->
            miRestaurante.copy(altaEstado = AltaEstado.ANALIZANDO, analisisEnCursoId = analisisId)
        }
    }
}
