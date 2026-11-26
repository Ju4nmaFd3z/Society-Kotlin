package com.alanturing.society.feature.empezamos.data.remote

import com.alanturing.society.feature.empezamos.model.Local
import com.alanturing.society.feature.empezamos.model.PlacePage
import com.alanturing.society.feature.empezamos.model.PlacePhoto

// Respuesta de GET /places/search
data class SearchResponse(
    val resultados: List<PlaceResponse>?,
    val nextPageToken: String?
)

// Un local de la búsqueda. Todo nullable: Gson no respeta los «no nulo» de Kotlin
// y algunos campos no llegan (valoracion sin reseñas, fotos sin fotos)
data class PlaceResponse(
    val placeId: String?,
    val nombre: String?,
    val direccion: String?,
    val valoracion: Double?,
    val numResenas: Int?,
    val fotos: List<PlacePhotoResponse>?,
    val googleMapsUri: String?
)

// Una foto de un local
data class PlacePhotoResponse(
    val name: String?, // referencia que se pasa a /places/photo, no es una URL
    val atribuciones: List<AttributionResponse>? // autores que hay que mostrar
)

// Autor de una foto
data class AttributionResponse(
    val nombre: String?,
    val uri: String?   // perfil del autor, puede no venir
)

// Respuesta de GET /places/photo
data class PhotoResponse(
    val photoUri: String?
)

// Pasa la respuesta de la API al modelo. mapNotNull transforma cada local
// y descarta los que vuelven null, así un resultado roto no tumba toda la búsqueda
fun SearchResponse.toModel() = PlacePage(
    locales = resultados.orEmpty().mapNotNull { it.toModel() },
    nextPageToken = nextPageToken
)

// Sin placeId no se puede dar de alta y sin nombre no hay qué pintar: se descarta.
// La API manda el nombre vacío ('') si Google no lo trae, por eso no basta con mirar el null
private fun PlaceResponse.toModel(): Local? {
    val id = placeId ?: return null
    val nombreLocal = nombre?.takeIf { it.isNotBlank() } ?: return null
    return Local(
        id = id,
        name = nombreLocal,
        address = direccion.orEmpty(),
        rating = valoracion ?: 0.0,          // Sin reseñas no llega: hasReviews ya lo tiene en cuenta
        userRatingCount = numResenas ?: 0,
        photos = fotos.orEmpty().mapNotNull { it.toModel() },
        googleMapsUri = googleMapsUri
    )
}

// Una foto sin name no se puede pedir a /places/photo: se descarta
private fun PlacePhotoResponse.toModel(): PlacePhoto? {
    val referencia = name ?: return null
    return PlacePhoto(
        name = referencia,
        authors = atribuciones.orEmpty().mapNotNull { it.nombre }
    )
}