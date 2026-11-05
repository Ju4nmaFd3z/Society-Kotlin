package com.alanturing.society.feature.asiteveinternet

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alanturing.society.feature.asiteveinternet.ui.AsiTeVeInternetScreen
import com.alanturing.society.navigation.AsiTeVeInternetRoute

fun NavController.navigateToAsiTeVeInternet(restauranteId: String, modoEdicion: Boolean)
    = navigate(AsiTeVeInternetRoute(restauranteId, modoEdicion))

fun NavGraphBuilder.asiTeVeInternetDestination(onConfirmar: () -> Unit) {
    composable<AsiTeVeInternetRoute> { entry ->
        val route = entry.toRoute<AsiTeVeInternetRoute>() // saca los datos que trae la ruta
        AsiTeVeInternetScreen(
            restauranteId = route.restauranteId,
            onConfirmar = onConfirmar
        )
    }
}