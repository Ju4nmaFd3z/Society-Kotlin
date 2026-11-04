package com.alanturing.society.navigation

import kotlinx.serialization.Serializable

@Serializable
object Inicio // 01 · Inicio (bienvenida), punto de partida de la app, no recibe datos

@Serializable
data class Analisis(val localId: String) // 03 · Análisis, recibe solo el ID del local

// Pantallas de cartón piedra, sin datos todavía. El número es el del diseño en Figma.
@Serializable
object Empezamos // 02 · Busca tu local

@Serializable
object AsiTeVeInternet // 04

@Serializable
object Home // 05

@Serializable
object CrearContenido // 06

@Serializable
object EleccionEstilo // 07

@Serializable
object FotosLocal // 08

@Serializable
object ContenidoCreado // 09

@Serializable
object Notificaciones // 10

@Serializable
object CampanaPropuesta // 11

@Serializable
object CampanaMedida // 12

@Serializable
object Perfil // 13

@Serializable
object Estadisticas // 14

@Serializable
object Calendario // 15
