package com.alanturing.society.core.network

import com.alanturing.society.feature.acceso.data.remote.SocietyAuthApi
import com.alanturing.society.feature.empezamos.data.remote.PhotoResponse
import com.alanturing.society.feature.empezamos.data.remote.SearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
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
    suspend fun miRestaurante(): Response<MiRestauranteResponse>

    // Da de alta el local elegido en la búsqueda. 409 si ya es de otra cuenta o el usuario ya tiene uno
    @POST("restaurantes")
    suspend fun createRestaurant(@Body body: CreateRestaurantRequest): Response<RestauranteResumen>

    // Crea un analisis en estado EN_COLA y un trabajo ANALISIS para el worker.
    @POST("restaurantes/{id}/analisis")
    suspend fun lanzarAnalisis(@Path("id") restauranteId: String): Response<AnalisisLanzadoResponse>

    // Busca locales por texto. Sin pageToken pide la primera página; con él, la siguiente.
    // Si pageToken es null, Retrofit no lo añade a la URL
    @GET("places/search")
    suspend fun searchPlaces(
        @Query("q") query: String,
        @Query("pageToken") pageToken: String? = null
    ): Response<SearchResponse>

    // Devuelve la URL de una foto a partir del name que trae cada foto de la búsqueda
    @GET("places/photo")
    suspend fun placePhoto(
        @Query("name") name: String,
        @Query("maxWidthPx") maxWidthPx: Int
    ): Response<PhotoResponse>
}

data class AnalisisLanzadoResponse(val analisisId: String?)

data class MiRestauranteResponse(
    val restaurante: RestauranteResumen?,
    val altaEstado: String?,
    val analisisEnCursoId: String?
)

data class RestauranteResumen(val id: String?)

// Cuerpo de POST /restaurantes para el alta desde Google (AltaGooglePeticion del contrato).
// declaraGestor es la casilla «Soy propietario o gestor»: la API solo acepta true
data class CreateRestaurantRequest(
    val googlePlaceId: String,
    val origen: String = "GOOGLE",
    val declaraGestor: Boolean = true
)
