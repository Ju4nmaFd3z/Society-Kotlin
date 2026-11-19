package com.alanturing.society.feature.arranque.data.remote

import com.alanturing.society.core.network.MiRestauranteResponse
import com.alanturing.society.feature.arranque.model.AltaEstado
import com.alanturing.society.feature.arranque.model.MiRestaurante

// Pasa la respuesta de la API a la clase que usa el resto de la app.
// Devuelve null si la API manda un altaEstado que la app no conoce
fun MiRestauranteResponse.toModel(): MiRestaurante? {
    val estado = AltaEstado.entries.firstOrNull { it.name == altaEstado } ?: return null
    return MiRestaurante(
        altaEstado = estado,
        restauranteId = restaurante?.id,
        analisisEnCursoId = analisisEnCursoId
    )
}
