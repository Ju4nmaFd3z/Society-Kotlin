package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.core.network.CreateRestaurantRequest
import com.alanturing.society.core.network.SocietyApi
import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject

class RestauranteRemoteDataSource @Inject constructor(
    private val api: SocietyApi
) : RestauranteDataSource {

    override suspend fun miRestaurante(): RespuestaMio {
        return try {
            val respuesta = api.miRestaurante() // el interceptor pone el token y renueva si hace falta
            val modelo = respuesta.body()?.toModel()
            when {
                respuesta.isSuccessful && modelo != null -> RespuestaMio.Ok(modelo)
                respuesta.code() == 401 -> RespuestaMio.SesionCaducada
                else -> RespuestaMio.Error
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            RespuestaMio.Error
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            RespuestaMio.Error
        }
    }

    override suspend fun createRestaurant(googlePlaceId: String): CreateResult {
        return try {
            val respuesta = api.createRestaurant(CreateRestaurantRequest(googlePlaceId))
            val id = respuesta.body()?.id
            when {
                respuesta.isSuccessful && id != null -> CreateResult.Created(id)
                respuesta.code() == 409 -> CreateResult.Duplicate
                respuesta.code() == 401 -> CreateResult.SessionExpired
                else -> CreateResult.Error
            }
        } catch (e: IOException) {
            CreateResult.Error
        } catch (e: JsonParseException) {
            CreateResult.Error
        }
    }

    override suspend fun lanzarAnalisis(restauranteId: String): String? {
        return try {
            val respuesta = api.lanzarAnalisis(restauranteId)
            if (respuesta.isSuccessful) respuesta.body()?.analisisId else null
        } catch (e: IOException) {
            null
        } catch (e: JsonParseException) {
            null
        }
    }
}
