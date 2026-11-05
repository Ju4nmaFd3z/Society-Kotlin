package com.alanturing.society.feature.campanapropuesta

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alanturing.society.feature.campanapropuesta.ui.CampanaPropuestaScreen
import com.alanturing.society.navigation.CampanaPropuestaRoute

// Lo llama quien quiera navegar aquí, con la oportunidad que se va a ver
fun NavController.navigateToCampanaPropuesta(oportunidadId: String) =
    navigate(CampanaPropuestaRoute(oportunidadId))

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.campanaPropuestaDestination(onCrearCampana: () -> Unit) {
    composable<CampanaPropuestaRoute> { entry ->
        val route = entry.toRoute<CampanaPropuestaRoute>() // saca los datos que trae la ruta
        CampanaPropuestaScreen(
            oportunidadId = route.oportunidadId,
            onCrearCampana = onCrearCampana
        )
    }
}
