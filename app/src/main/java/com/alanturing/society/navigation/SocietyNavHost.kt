package com.alanturing.society.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alanturing.society.feature.analisis.ui.AnalisisScreen
import com.alanturing.society.feature.inicio.ui.InicioScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alanturing.society.feature.empezamos.ui.EmpezamosScreen
import com.alanturing.society.feature.inicio.ui.InicioViewModel
import com.alanturing.society.ui.component.Destino
import com.alanturing.society.ui.component.PantallaProvisional

@Composable
fun SocietyNavHost(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = EmpezamosRoute,
        modifier = modifier,
    ) {
        empezamosDestination(
            onAnalisisLanzado = { id -> navController.navigateToAnalisis(id)},
            onAltamanual = { navController.navigateToAltaManual()}
        )

        analisisDestination(
            onTerminado = {navController.navigateToAsiTeVeInternet()},
            onCancelar = { navController.popBackStack() },
        )
    }
}