package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.core.network.SocietyApi
import com.alanturing.society.feature.acceso.data.remote.exception.NoConnectionException
import com.alanturing.society.feature.acceso.data.remote.exception.ServerErrorException
import com.alanturing.society.feature.acceso.data.remote.exception.SessionExpiredException
import com.alanturing.society.feature.arranque.model.MiRestaurante
import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject

class RestauranteRemoteDataSource @Inject constructor(
    private val api: SocietyApi
) : RestauranteDataSource {

    override suspend fun miRestaurante(): Result<MiRestaurante> {
        return try {
            val respuesta = api.miRestaurante() // el interceptor pone el token y renueva si hace falta
            val modelo = respuesta.body()?.toModel()
            when {
                respuesta.isSuccessful && modelo != null -> Result.success(modelo)
                respuesta.code() == 401 -> Result.failure(SessionExpiredException())
                else -> Result.failure(ServerErrorException())
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            Result.failure(NoConnectionException())
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            Result.failure(ServerErrorException())
        }
    }

    override suspend fun lanzarAnalisis(restauranteId: String): Result<String> {
        return try {
            val respuesta = api.lanzarAnalisis(restauranteId)
            val analisisId = respuesta.body()?.analisisId
            if (respuesta.isSuccessful && analisisId != null) {
                Result.success(analisisId)
            } else {
                Result.failure(ServerErrorException())
            }
        } catch (e: IOException) {
            Result.failure(NoConnectionException())
        } catch (e: JsonParseException) {
            Result.failure(ServerErrorException())
        }
    }
}
