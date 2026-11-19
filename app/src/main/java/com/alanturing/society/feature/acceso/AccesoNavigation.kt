package com.alanturing.society.feature.acceso

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.acceso.ui.AccesoScreen
import com.alanturing.society.feature.acceso.ui.AccesoUiState
import com.alanturing.society.navigation.AccesoRoute
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alanturing.society.feature.acceso.ui.AccesoViewModel

// Lo llama quien quiera navegar aquí
fun NavController.navigateToAcceso() = navigate(AccesoRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.accesoDestination(onSesionIniciada: (Any) -> Unit) { // recibe la ruta a la que ir
    composable<AccesoRoute> {

        val viewModel: AccesoViewModel = hiltViewModel()
        val estado by viewModel.estado.collectAsState()

        //La navegacions se ejecuta cuando cambia el estado
        LaunchedEffect(estado){
            val actual = estado
            if(actual is AccesoUiState.Hecho){
                viewModel.navegacionHecha()
                onSesionIniciada(actual.destino)
            }
        }
        // La pantalla pinta el estado y avisa de lo que pulsa el usuario
        AccesoScreen(
            estado=estado,
            onContinuar=viewModel::continuar
        )

    }
}
