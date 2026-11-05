package com.alanturing.society.feature.publicarcontenido

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alanturing.society.feature.publicarcontenido.ui.PublicarContenidoScreen
import com.alanturing.society.navigation.PublicarContenidoRoute

// Lo llama quien quiera navegar aquí, con la pieza que se va a publicar
fun NavController.navigateToPublicarContenido(piezaId: String) =
    navigate(PublicarContenidoRoute(piezaId))

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.publicarContenidoDestination(onPublicado: () -> Unit) {
    composable<PublicarContenidoRoute> { entry ->
        val route = entry.toRoute<PublicarContenidoRoute>() // saca los datos que trae la ruta
        PublicarContenidoScreen(
            piezaId = route.piezaId,
            onPublicado = onPublicado
        )
    }
}
