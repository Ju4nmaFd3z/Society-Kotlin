package com.alanturing.society.feature.empezamos.model

// Data class con lo que pinta la tarjeta: identificador, nombre, media de reseñas, número de reseñas, calle y fotos
// No tener anotaciones de red ni de serialización

data class Local (
    val id: String,             // placeId de Google: es lo que se manda al dar de alta el local
    val name: String,
    val address: String,
    val rating: Double,         // Media de reseñas
    val userRatingCount: Int,   // Número total de reseñas
    val photos: List<PlacePhoto>,
    val googleMapsUri: String?  // Enlace «Google Maps» de la tarjeta, puede no venir
) {

    // True si el local tiene al menos una foto
    val hasPhotos: Boolean get() = photos.isNotEmpty()

    // Un local sin reseñas no tiene media que mostrar
    val hasReviews: Boolean get() = userRatingCount > 0

    // Media con un decimal o null si no hay reseñas
    val ratingText: String? get() = if (hasReviews) "%.1f".format(rating) else null
}

// Una foto del local: la referencia para pedir su URL a /places/photo y quién la hizo
data class PlacePhoto(
    val name: String,
    val authors: List<String>   // Nombres de los autores, que hay que mostrar junto a la foto
)

// Una página de resultados de la búsqueda. Si nextPageToken es null, no hay más páginas
data class PlacePage(
    val locales: List<Local>,
    val nextPageToken: String?
)
