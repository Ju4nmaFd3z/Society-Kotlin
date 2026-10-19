package com.alanturing.society.feature.inicio.model

import androidx.annotation.IntegerRes

// Data class con lo que pinta la tarjeta: identificador, nombre,
// media de reseñas, número de reseñas, calle y fotos.
// No tener anotaciones de red ni de serialización.

data class Local (
    val id: String,
    val name: String,
    val address: String,
    val rating: Int,            // media de resenas
    val userRatingCount: Int,   // Numero total de resenas
    val photos: List<String>    // Array no implementa equals() ni hashCode() por contenido.
) {



}
