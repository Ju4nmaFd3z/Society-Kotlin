package com.alanturing.society.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.home.ui.HomeScreen
import com.alanturing.society.navigation.HomeRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToHome() = navigate(HomeRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.homeDestination(
    onCrearContenido: () -> Unit,
    onOportunidad: () -> Unit,
    onPieza: () -> Unit,
    onCampanaMedida: () -> Unit,
    onCalendario: () -> Unit,
    onPerfil: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            onCrearContenido = onCrearContenido,
            onOportunidad = onOportunidad,
            onPieza = onPieza,
            onCampanaMedida = onCampanaMedida,
            onCalendario = onCalendario,
            onPerfil = onPerfil
        )
    }
}
