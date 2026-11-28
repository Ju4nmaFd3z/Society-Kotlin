package com.alanturing.society.feature.empezamos

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.empezamos.ui.EmpezamosScreen
import com.alanturing.society.feature.empezamos.ui.EmpezamosViewModel
import com.alanturing.society.navigation.AccesoRoute
import com.alanturing.society.navigation.AnalisisRoute
import com.alanturing.society.navigation.EmpezamosRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToEmpezamos() = navigate(EmpezamosRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.empezamosDestination(
    onAnalisisLanzado: (analisisId: String) -> Unit,
    onAltaManual: () -> Unit,
    onSesionCaducada: () -> Unit,
) {
    composable<EmpezamosRoute> {
        val viewModel: EmpezamosViewModel = hiltViewModel()
        val estado by viewModel.estado.collectAsState() // cada vez que el viweModel cambia el estado, la pantalla se vuelve a dibujar
        val confirm by viewModel.confirm.collectAsState()
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

        EmpezamosScreen(
            estado = estado,
            confirm = confirm,
            onBuscar = viewModel::buscar,
            onReintentar = viewModel::reintentar,
            onVerMas = viewModel::verMas,
            onEsMiLocal = viewModel::esMiLocal,
            onConfirmarLocal = viewModel::confirmarLocal,
            onCerrarModal = viewModel::cerrarModal,
            onAltaManual = onAltaManual,
        )
    }
}
