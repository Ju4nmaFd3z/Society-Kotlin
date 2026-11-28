package com.alanturing.society.feature.arranque.model

// Tipos de local que acepta la API. Los nombres son los valores del contrato, tal cual se envían
enum class PlaceType { RESTAURANTE, BAR, CAFETERIA, OTRO }

// Lo que hace falta para dar de alta un local a mano (S02M). Ya validado y sin espacios de más
data class ManualRestaurant(
    val name: String,
    val type: PlaceType,
    val address: String,
    val town: String,
    val web: String?,       // null si no se ha escrito
    val instagram: String?  // sin la @
)
