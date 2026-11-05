package com.alanturing.society.navigation

import kotlinx.serialization.Serializable

// 01 Bienvenida. Punto de partida de la app si no hay sesión. No recibe datos
@Serializable
data object BienvenidaRoute

// 01A Acceso, crear cuenta o iniciar sesión. No recibe datos
@Serializable
data object AccesoRoute

// 02 Busca tu local. No recibe datos
@Serializable
data object EmpezamosRoute

// 02M Alta manual, si el local no aparece en la búsqueda. No recibe datos
@Serializable
data object AltaManualRoute

// 03 Análisis. Recibe el ID del análisis que se ha lanzado en 02 o 02M
@Serializable
data class AnalisisRoute(val analisisId: String)

// 04 Así te ve internet. Llega desde Análisis (alta) o desde Perfil (editar la ficha)
@Serializable
data class AsiTeVeInternetRoute(
    val restauranteId: String,
    val modoEdicion: Boolean = false // Para que se active cuando entre desde el Perfil
)

// 05 Home. No recibe datos
@Serializable
data object HomeRoute

// 06 a 08 Crear contenido. Grafo que agrupa los tres pasos para que compartan el borrador.
// Recibe el ID de la campaña solo si se llega desde 11, para precargar la idea
@Serializable
data class CrearContenidoGraph(val campanaId: String? = null)

// 06 Crear contenido, idea y tipo. No recibe datos, usa los del grafo
@Serializable
data object CrearContenidoRoute

// 07 Elección de estilo. No recibe datos, usa el borrador del grafo
@Serializable
data object EleccionEstiloRoute

// 08 Fotos del local. No recibe datos, usa el borrador del grafo
@Serializable
data object FotosLocalRoute

// 09 Contenido creado. Recibe el ID de la pieza generada
@Serializable
data class ContenidoCreadoRoute(val piezaId: String)

// 09P Publicar contenido. Recibe el ID de la pieza que se va a publicar
@Serializable
data class PublicarContenidoRoute(val piezaId: String)

// 11 Campaña propuesta. Recibe el ID de la oportunidad que se ha tocado en Home
@Serializable
data class CampanaPropuestaRoute(val oportunidadId: String)

// 12 Campaña a medida (P1). No recibe datos
@Serializable
data object CampanaMedidaRoute

// 13 Perfil. No recibe datos
@Serializable
data object PerfilRoute

// 15 Calendario (P1). No recibe datos
@Serializable
data object CalendarioRoute
