package com.alanturing.society.feature.acceso

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.acceso.ui.AccesoScreen
import com.alanturing.society.navigation.AccesoRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToAcceso() = navigate(AccesoRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.accesoDestination(onSesionIniciada: () -> Unit) {
    composable<AccesoRoute> {
        AccesoScreen(onSesionIniciada = onSesionIniciada)
    }
}
