package com.alanturing.society.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alanturing.society.feature.acceso.accesoDestination
import com.alanturing.society.feature.acceso.navigateToAcceso
import com.alanturing.society.feature.altamanual.altaManualDestination
import com.alanturing.society.feature.altamanual.navigateToAltaManual
import com.alanturing.society.feature.analisis.analisisDestination
import com.alanturing.society.feature.analisis.navigateToAnalisis
import com.alanturing.society.feature.asiteveinternet.asiTeVeInternetDestination
import com.alanturing.society.feature.asiteveinternet.navigateToAsiTeVeInternet
import com.alanturing.society.feature.bienvenida.bienvenidaDestination
import com.alanturing.society.feature.bienvenida.navigateToBienvenida
import com.alanturing.society.feature.calendario.calendarioDestination
import com.alanturing.society.feature.calendario.navigateToCalendario
import com.alanturing.society.feature.campanamedida.campanaMedidaDestination
import com.alanturing.society.feature.campanamedida.navigateToCampanaMedida
import com.alanturing.society.feature.campanapropuesta.campanaPropuestaDestination
import com.alanturing.society.feature.campanapropuesta.navigateToCampanaPropuesta
import com.alanturing.society.feature.contenidocreado.contenidoCreadoDestination
import com.alanturing.society.feature.contenidocreado.navigateToContenidoCreado
import com.alanturing.society.feature.crearcontenidograph.crearContenidoGraph
import com.alanturing.society.feature.crearcontenidograph.navigateToCrearContenido
import com.alanturing.society.feature.crearcontenidograph.navigateToEleccionEstilo
import com.alanturing.society.feature.crearcontenidograph.navigateToFotosLocal
import com.alanturing.society.feature.empezamos.empezamosDestination
import com.alanturing.society.feature.empezamos.navigateToEmpezamos
import com.alanturing.society.feature.home.homeDestination
import com.alanturing.society.feature.home.navigateToHome
import com.alanturing.society.feature.perfil.navigateToPerfil
import com.alanturing.society.feature.perfil.perfilDestination
import com.alanturing.society.feature.publicarcontenido.navigateToPublicarContenido
import com.alanturing.society.feature.publicarcontenido.publicarContenidoDestination

@Composable
fun SocietyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BienvenidaRoute, // TODO: elegir el destino inicial según la sesión y el estado del alta
        modifier = modifier,
    ) {
        bienvenidaDestination(
            onDescubrir = { navController.navigateToAcceso() }
        )

        accesoDestination(
            onSesionIniciada = { navController.navigateToEmpezamos() } // TODO: ir a 02, 03, 04 o 05 según el estado del alta
        )

        empezamosDestination(
            onAnalisisLanzado = { id -> navController.navigateToAnalisis(id) },
            onAltaManual = { navController.navigateToAltaManual() }
        )

        altaManualDestination(
            onDatosGuardados = { navController.navigateToAnalisis("analisis_demo") } // TODO: analisisId real que devuelve la API
        )

        analisisDestination(
            onTerminado = { navController.navigateToAsiTeVeInternet(
                "restaurante_demo", // TODO: restauranteId real del análisis
                false) },
            onCancelar = { navController.popBackStack() },
        )

        asiTeVeInternetDestination (
            onConfirmar = { navController.navigateToHome() } // TODO: vaciar la pila del alta, o volver a Perfil si viene en modo edición
        )

        homeDestination(
            onCrearContenido = { navController.navigateToCrearContenido() },
            onOportunidad = { navController.navigateToCampanaPropuesta("oportunidad_demo") }, // TODO: oportunidadId de la tarjeta tocada
            onPieza = { navController.navigateToContenidoCreado("pieza_demo") }, // TODO: piezaId de la pieza tocada
            onCampanaMedida = { navController.navigateToCampanaMedida() },
            onCalendario = { navController.navigateToCalendario() },
            onPerfil = { navController.navigateToPerfil() },
        )

        crearContenidoGraph(
            onIdeaLista = { navController.navigateToEleccionEstilo() },
            onEstiloElegido = { navController.navigateToFotosLocal() },
            onGenerar = { navController.navigateToContenidoCreado("pieza_demo") }, // TODO: piezaId real que devuelve la API
        )

        contenidoCreadoDestination(
            onPublicar = { piezaId -> navController.navigateToPublicarContenido(piezaId) }
        )

        publicarContenidoDestination(
            onPublicado = { navController.navigateToHome() } // TODO: volver a la Home que ya está en la pila, sin crear otra
        )

        campanaPropuestaDestination(
            onCrearCampana = { navController.navigateToCrearContenido("campana_demo") } // TODO: campanaId real que devuelve la API
        )

        campanaMedidaDestination(
            onPlanCreado = { navController.navigateToCampanaPropuesta("oportunidad_demo") } // TODO: ID real del plan creado
        )

        perfilDestination(
            onDatosLocal = { navController.navigateToAsiTeVeInternet("restaurante_demo", true) }, // TODO: restauranteId real del local
            onCerrarSesion = { navController.navigateToBienvenida() } // TODO: vaciar la pila para que atrás no vuelva al Perfil
        )

        calendarioDestination(
            onPieza = { navController.navigateToContenidoCreado("pieza_demo") } // TODO: piezaId de la pieza tocada
        )
    }
}
