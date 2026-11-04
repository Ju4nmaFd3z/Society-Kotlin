package com.alanturing.society.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alanturing.society.feature.analisis.analisisDestination
import com.alanturing.society.feature.analisis.navigateToAnalisis
import com.alanturing.society.feature.empezamos.EmpezamosRoute
import com.alanturing.society.feature.empezamos.empezamosDestination

@Composable
fun SocietyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = EmpezamosRoute,
        modifier = modifier,
    ) {
        empezamosDestination(
            onAnalisisLanzado = { id -> navController.navigateToAnalisis(id) },
            onAltaManual = { /* TODO navController.navigateToAltaManual() */ }
        )

        analisisDestination(
            onTerminado = { /* TODO navController.navigateToAsiTeVeInternet() */ },
            onCancelar = { navController.popBackStack() },
        )
    }
}
