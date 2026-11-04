package com.alanturing.society.feature.analisis

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alanturing.society.feature.analisis.ui.AnalisisScreen
import kotlinx.serialization.Serializable

// Ruta de S03 · Estudiando tu local. Recibe el ID del análisis
@Serializable
data class AnalisisRoute(val analisisId: String)

// Lo llama quien quiera navegar aquí
fun NavController.navigateToAnalisis(analisisId: String) =
    navigate(AnalisisRoute(analisisId))

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.analisisDestination(
    onTerminado: () -> Unit,
    onCancelar: () -> Unit
) {
    composable<AnalisisRoute> { entry ->
        val route = entry.toRoute<AnalisisRoute>()
        AnalisisScreen(
            analisisId = route.analisisId,
            onTerminado = onTerminado,
            onCancelar = onCancelar
        )
    }
}
