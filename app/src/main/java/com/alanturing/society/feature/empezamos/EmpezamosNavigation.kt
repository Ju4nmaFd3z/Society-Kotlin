package com.alanturing.society.feature.empezamos

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

data object EmpezamosNavigation {
    fun NavController.navigateToEmpezamos() = navigate(EmpezamosRoute)

    fun NavGraphBuilder.empezamosDestination(
        onAnalisisLanzado: (analisisId: String) -> Unit,
        onAltaManual: () -> Unit,
    ) {
        composable<EmpezamosRoute> {
            EmpezamosScreen(
                onAnalisisLanzado = onAnalisisLanzado,
                onAltaManual = onAltaManual,
            )
        }
}