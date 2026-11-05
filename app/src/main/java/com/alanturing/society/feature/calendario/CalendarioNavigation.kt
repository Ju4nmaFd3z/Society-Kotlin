package com.alanturing.society.feature.calendario

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.calendario.ui.CalendarioScreen
import com.alanturing.society.navigation.CalendarioRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToCalendario() = navigate(CalendarioRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.calendarioDestination(onPieza: () -> Unit) {
    composable<CalendarioRoute> {
        CalendarioScreen(onPieza = onPieza)
    }
}
