package com.alanturing.society.feature.inicio.model

// Data class con lo que pinta la tarjeta: identificador, nombre, media de reseñas, número de reseñas, calle y fotos
// No tener anotaciones de red ni de serialización

data class Local (
    val id: String,
    val name: String,
    val address: String,
    val rating: Double,         // Media de reseñas
    val userRatingCount: Int,   // Número total de reseñas
    val photos: List<String>    // Array no implementa equals() ni hashCode() por contenido
) {

    // True si el local tiene al menos una foto
    val hasPhotos: Boolean get() = photos.isNotEmpty()

    // Un local sin reseñas no tiene media que mostrar
    val hasReviews: Boolean get() = userRatingCount > 0

    // Media con un decimal o null si no hay reseñas
    val ratingText: String? get() = if (hasReviews) "%.1f".format(rating) else null
}