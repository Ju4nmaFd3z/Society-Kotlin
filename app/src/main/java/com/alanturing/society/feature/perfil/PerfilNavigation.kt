package com.alanturing.society.feature.perfil

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.alanturing.society.feature.perfil.ui.PerfilScreen
import com.alanturing.society.navigation.PerfilRoute

// Lo llama quien quiera navegar aquí
fun NavController.navigateToPerfil() = navigate(PerfilRoute)

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.perfilDestination(
    onDatosLocal: () -> Unit,
    onCerrarSesion: () -> Unit,
) {
    composable<PerfilRoute> {
        PerfilScreen(
            onDatosLocal = onDatosLocal,
            onCerrarSesion = onCerrarSesion
        )
    }
}
