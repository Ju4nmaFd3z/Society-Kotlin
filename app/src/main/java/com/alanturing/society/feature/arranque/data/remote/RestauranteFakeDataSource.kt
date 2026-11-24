package com.alanturing.society.feature.arranque.data.remote

import kotlinx.coroutines.delay
import java.util.UUID
import javax.inject.Inject

/**
 * Simula POST /restaurantes y POST /restaurantes/{id}/analisis mientras no estén en la API
 * (SOCIETY-58 y SOCIETY-60). Responde con la forma del contrato, así que al cambiar a
 * RestauranteRemoteDataSource en AppModule no hay que tocar nada más.
 *
 * GET /restaurantes/mio ya existe, así que ese sí va a la API real.
 */
class RestauranteFakeDataSource @Inject constructor(
    private val remote: RestauranteRemoteDataSource
) : RestauranteDataSource {

    // Local dado de alta en esta sesión. Sirve para simular el 409:
    // un usuario solo puede tener un local (D-13)
    private var restauranteCreado: String? = null

    override suspend fun miRestaurante(): RespuestaMio = remote.miRestaurante()

    override suspend fun createRestaurant(googlePlaceId: String): CreateResult {
        delay(800) // para que se vea el estado «guardando»
        if (restauranteCreado != null) return CreateResult.Duplicate

        val id = UUID.randomUUID().toString()
        restauranteCreado = id
        return CreateResult.Created(id)
    }

    override suspend fun lanzarAnalisis(restauranteId: String): String {
        delay(400)
        return UUID.randomUUID().toString()
    }
}
