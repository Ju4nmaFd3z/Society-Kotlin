package com.alanturing.society.feature.empezamos

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.empezamos.ui.EmpezamosScreen
import com.alanturing.society.feature.empezamos.ui.EmpezamosViewModel
import kotlinx.serialization.Serializable

// Ruta de S02 · Busca tu local. No recibe datos
@Serializable
data object EmpezamosRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToEmpezamos() = navigate(EmpezamosRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.empezamosDestination(
    onAnalisisLanzado: (analisisId: String) -> Unit,
    onAltaManual: () -> Unit,
) {
    composable<EmpezamosRoute> {
        val viewModel: EmpezamosViewModel = hiltViewModel()
        val estado by viewModel.estado.collectAsState()
        EmpezamosScreen(
            estado = estado,
            // TODO el ViewModel lanzará el análisis y devolverá su analisisId
            onAnalizar = onAnalisisLanzado,
            onAltaManual = onAltaManual,
        )
    }
}
