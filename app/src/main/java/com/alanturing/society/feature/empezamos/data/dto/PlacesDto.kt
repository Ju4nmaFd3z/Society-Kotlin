package com.alanturing.society.feature.empezamos.data.dto

import kotlinx.serialization.Serializable

// Respuesta tal y como llega en locales.json, con la misma forma que la de Google Places

@Serializable
data class PlacesResponseDto(val places: List<PlaceDto> = emptyList())

@Serializable
data class PlaceDto(
    val id: String,
    val displayName: DisplayNameDto,
    val formattedAddress: String = "",
    val rating: Double = 0.0,
    val userRatingCount: Int = 0,
    val photos: List<PhotoDto> = emptyList()
)

@Serializable
data class DisplayNameDto(val text: String)

@Serializable
data class PhotoDto(val name: String) // URL de la foto
