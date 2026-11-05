package com.alanturing.society.feature.crearcontenidograph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alanturing.society.feature.crearcontenidograph.ui.CrearContenidoScreen
import com.alanturing.society.feature.crearcontenidograph.ui.EleccionEstiloScreen
import com.alanturing.society.feature.crearcontenidograph.ui.FotosLocalScreen
import com.alanturing.society.navigation.CrearContenidoGraph
import com.alanturing.society.navigation.CrearContenidoRoute
import com.alanturing.society.navigation.EleccionEstiloRoute
import com.alanturing.society.navigation.FotosLocalRoute

// Entra al grafo, que empieza siempre en 06. campanaId solo llega desde Campaña propuesta
fun NavController.navigateToCrearContenido(campanaId: String? = null) =
    navigate(CrearContenidoGraph(campanaId))

// Pasos dentro del grafo
fun NavController.navigateToEleccionEstilo() = navigate(EleccionEstiloRoute)
fun NavController.navigateToFotosLocal() = navigate(FotosLocalRoute)

// Los tres pasos van dentro del mismo grafo para que compartan el borrador
fun NavGraphBuilder.crearContenidoGraph(
    onIdeaLista: () -> Unit,
    onEstiloElegido: () -> Unit,
    onGenerar: () -> Unit,
) {
    navigation<CrearContenidoGraph>(startDestination = CrearContenidoRoute) {
        // TODO: un CrearContenidoViewModel compartido por los tres pasos guardará el borrador
        composable<CrearContenidoRoute> {
            CrearContenidoScreen(onSiguiente = onIdeaLista)
        }
        composable<EleccionEstiloRoute> {
            EleccionEstiloScreen(onSiguiente = onEstiloElegido)
        }
        composable<FotosLocalRoute> {
            FotosLocalScreen(onGenerar = onGenerar)
        }
    }
}
