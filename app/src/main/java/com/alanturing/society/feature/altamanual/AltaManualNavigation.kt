package com.alanturing.society.feature.altamanual

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.altamanual.ui.AltaManualScreen
import com.alanturing.society.navigation.AltaManualRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToAltaManual() = navigate(AltaManualRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.altaManualDestination(onDatosGuardados: () -> Unit) {
    composable<AltaManualRoute> {
        AltaManualScreen(onDatosGuardados = onDatosGuardados)
    }
}
