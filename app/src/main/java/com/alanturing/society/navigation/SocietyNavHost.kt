package com.alanturing.society.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.alanturing.society.feature.inicio.ui.InicioScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alanturing.society.feature.inicio.ui.InicioViewModel

@Composable
fun SocietyNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // remember hace que compose conserve la pantalla entre redibujados.
    NavHost(
        navController = navController, // le dice que conducto usar
        startDestination = Inicio, // Con qué ruta comenzar, le pasa un objeto
        modifier = modifier
    ) {
        composable<Inicio> { // cuando se navega a Inicio
            val viewModel: InicioViewModel = hiltViewModel() // pide a hilt un ViewModel
            val estado by viewModel.estado.collectAsState() // stateFlow (valor que cambia con el tiempo) y collect lo recompone en composable cuando cambian los valores.
            InicioScreen(
                estado = estado,
                // TODO cambiar al componente de buscador.
                onBuscar = { localId -> navController.navigate(Analisis(localId))}

            )
        }
    }
}

