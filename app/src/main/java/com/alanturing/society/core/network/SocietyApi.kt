package com.alanturing.society.core.network

import retrofit2.Response
import retrofit2.http.GET

/**
 * Todas las llamadas a la API de Society. Cada función devuelve un Response<T>,
 * así el DataSource decide qué hacer con un error HTTP en vez de que salte una excepción.
 * Las rutas no llevan barra inicial: así Retrofit las suma a API_BASE (/api/v1/).
 */
interface SocietyApi {

    // Comprueba que la API responde
    @GET("salud")
    suspend fun salud(): Response<SaludResponse>
}
