package com.alanturing.society.feature.bienvenida

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.bienvenida.ui.BienvenidaScreen
import com.alanturing.society.navigation.BienvenidaRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToBienvenida() = navigate(BienvenidaRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.bienvenidaDestination(onDescubrir: () -> Unit) {
    composable<BienvenidaRoute> {
        BienvenidaScreen(onDescubrir = onDescubrir)
    }
}
