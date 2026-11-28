package com.alanturing.society.feature.altamanual

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.altamanual.ui.AltaManualScreen
import com.alanturing.society.feature.altamanual.ui.AltaManualViewModel
import com.alanturing.society.navigation.AccesoRoute
import com.alanturing.society.navigation.AltaManualRoute
import com.alanturing.society.navigation.AnalisisRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToAltaManual() = navigate(AltaManualRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.altaManualDestination(
    onAnalisisLanzado: (analisisId: String) -> Unit,
    onVolver: () -> Unit,
    onSesionCaducada: () -> Unit,
) {
    composable<AltaManualRoute> {
        val viewModel: AltaManualViewModel = hiltViewModel()
        val estado by viewModel.estado.collectAsState()
        val destino by viewModel.destino.collectAsState()

        // Navega una sola vez cuando el ViewModel elige destino
        LaunchedEffect(destino) {
            when (val ruta = destino) {
                is AnalisisRoute -> onAnalisisLanzado(ruta.analisisId)
                AccesoRoute -> onSesionCaducada()
                else -> return@LaunchedEffect
            }
            viewModel.navegacionHecha()
        }

        AltaManualScreen(
            estado = estado,
            onNameChange = viewModel::onNameChange,
            onTypeChange = viewModel::onTypeChange,
            onAddressChange = viewModel::onAddressChange,
            onTownChange = viewModel::onTownChange,
            onWebChange = viewModel::onWebChange,
            onInstagramChange = viewModel::onInstagramChange,
            onManagerChange = viewModel::onManagerChange,
            onGuardar = viewModel::guardar,
            onVolver = onVolver,
        )
    }
}
