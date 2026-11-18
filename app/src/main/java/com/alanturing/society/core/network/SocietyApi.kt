package com.alanturing.society.core.network

import com.alanturing.society.feature.acceso.data.remote.SocietyAuthApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Todas las llamadas a la API de Society. Cada función devuelve un Response<T>,
 * así el DataSource decide qué hacer con un error HTTP en vez de que salte una excepción.
 * Las rutas no llevan barra inicial: así Retrofit las suma a API_BASE (/api/v1/).
 */
interface SocietyApi : SocietyAuthApi {

    // Comprueba que la API responde
    @GET("salud")
    suspend fun salud(): Response<SaludResponse>

    @GET("restaurantes/mio")
    suspend fun miRestaurante(@Header("Authorization") bearer: String): Response<MiRestauranteResponse>

}

data class MiRestauranteResponse(
    val restaurante: RestauranteResumen?,
    val altaEstado: String?,
    val analisisEnCursoId: String?
)

data class RestauranteResumen(val id: String?)
