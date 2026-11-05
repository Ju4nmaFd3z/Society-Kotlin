package com.alanturing.society.feature.contenidocreado

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.alanturing.society.feature.contenidocreado.ui.ContenidoCreadoScreen
import com.alanturing.society.navigation.ContenidoCreadoRoute

// Lo llama quien quiera navegar aquí, con la pieza que se va a revisar
fun NavController.navigateToContenidoCreado(piezaId: String) =
    navigate(ContenidoCreadoRoute(piezaId))

// Lo que se muestra cuando la ruta está activa
fun NavGraphBuilder.contenidoCreadoDestination(onPublicar: (piezaId: String) -> Unit) {
    composable<ContenidoCreadoRoute> { entry ->
        val route = entry.toRoute<ContenidoCreadoRoute>() // saca los datos que trae la ruta
        ContenidoCreadoScreen(
            piezaId = route.piezaId,
            onPublicar = { onPublicar(route.piezaId) } // pasa la misma pieza a Publicar
        )
    }
}
