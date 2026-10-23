package com.alanturing.society.navigation

import kotlinx.serialization.Serializable

@Serializable
object Inicio // Punto de partida de la app, no recibe datos

@Serializable
data class Analisis(val localId: String) // Recibe solo el ID del local