package com.alanturing.society.feature.arranque

import com.alanturing.society.feature.arranque.model.AltaEstado
import com.alanturing.society.feature.arranque.model.MiRestaurante
import com.alanturing.society.navigation.AnalisisRoute
import com.alanturing.society.navigation.AsiTeVeInternetRoute
import com.alanturing.society.navigation.EmpezamosRoute
import com.alanturing.society.navigation.HomeRoute

// Ruta de destino según el punto del alta, o null si falta un dato que esa pantalla necesita
fun MiRestaurante.destino(): Any? = when (altaEstado) {
    AltaEstado.SIN_LOCAL -> EmpezamosRoute
    AltaEstado.CREADO -> null
    AltaEstado.ANALIZANDO -> analisisEnCursoId?.let { AnalisisRoute(it) }
    AltaEstado.PENDIENTE_CONFIRMAR -> restauranteId?.let { AsiTeVeInternetRoute(it) }
    AltaEstado.COMPLETADA -> HomeRoute
}
