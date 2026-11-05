package com.alanturing.society.feature.campanamedida

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.campanamedida.ui.CampanaMedidaScreen
import com.alanturing.society.navigation.CampanaMedidaRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToCampanaMedida() = navigate(CampanaMedidaRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.campanaMedidaDestination(onPlanCreado: () -> Unit) {
    composable<CampanaMedidaRoute> {
        CampanaMedidaScreen(onPlanCreado = onPlanCreado)
    }
}
