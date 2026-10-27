package com.alanturing.society.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alanturing.society.feature.analisis.ui.AnalisisScreen
import com.alanturing.society.feature.inicio.ui.InicioScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alanturing.society.feature.inicio.ui.InicioViewModel
import com.alanturing.society.ui.component.Destino
import com.alanturing.society.ui.component.PantallaProvisional

@Composable
fun SocietyNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // remember hace que compose conserve la pantalla entre redibujados.
    NavHost(
        navController = navController, // Le dice que conducto usar
        startDestination = Inicio, // Con qué ruta comenzar, le pasa un objeto (01, la bienvenida)
        modifier = modifier
    ) {
        // 01 · Inicio: bienvenida, de cartón piedra
        composable<Inicio> {
            PantallaProvisional(
                "01", "Inicio",
                destinos = listOf(Destino("Descubre cómo funciona") { navController.navigate(Empezamos) })
            )
        }

        // 02 · Empezamos: la pantalla real del buscador (su carpeta es feature/inicio)
        composable<Empezamos> { // Cuando se navega a Empezamos
            val viewModel: InicioViewModel = hiltViewModel() // Pide a hilt un ViewModel
            val estado by viewModel.estado.collectAsState() // stateFlow (valor que cambia con el tiempo) y collect lo recompone en composable cuando cambian los valores.
            InicioScreen(
                estado = estado,
                // TODO Cambiar al componente de buscador.
                onBuscar = { localId -> navController.navigate(Analisis(localId))},
                onAnalizar = { localId -> navController.navigate(Analisis(localId)) }
            )
        }

        composable<Analisis> { entrada ->
            val ruta = entrada.toRoute<Analisis>() // Recupera el localId que viaja en la ruta
            AnalisisScreen(
                localId = ruta.localId,
                onContinuar = { navController.navigate(AsiTeVeInternet) }
            )
        }

        // El resto son pantallas de cartón piedra, enlazadas según el flujo de Figma
        composable<AsiTeVeInternet> {
            PantallaProvisional(
                "04", "Así te ve Internet",
                destinos = listOf(Destino("Todo correcto, seguir") { navController.navigate(Home) })
            )
        }

        composable<Home> {
            PantallaProvisional(
                "05", "Home",
                destinos = listOf(
                    Destino("Notificaciones") { navController.navigate(Notificaciones) },
                    Destino("Campaña propuesta") { navController.navigate(CampanaPropuesta) },
                    Destino("Campaña a tu medida") { navController.navigate(CampanaMedida) },
                    Destino("Calendario") { navController.navigate(Calendario) },
                    Destino("Crear contenido (+)") { navController.navigate(CrearContenido) },
                    Destino("Estadísticas") { navController.navigate(Estadisticas) },
                    Destino("Perfil") { navController.navigate(Perfil) }
                )
            )
        }

        // Pasos de Crear contenido: 1 Idea, 2 Estética, 3 Local, y el resultado
        composable<CrearContenido> {
            PantallaProvisional(
                "06", "Crear contenido",
                destinos = listOf(Destino("Siguiente") { navController.navigate(EleccionEstilo) })
            )
        }
        composable<EleccionEstilo> {
            PantallaProvisional(
                "07", "Elección de estilo",
                destinos = listOf(Destino("Siguiente") { navController.navigate(FotosLocal) })
            )
        }
        composable<FotosLocal> {
            PantallaProvisional(
                "08", "Fotos del local",
                destinos = listOf(Destino("Siguiente") { navController.navigate(ContenidoCreado) })
            )
        }
        composable<ContenidoCreado> { PantallaProvisional("09", "Contenido creado") }

        composable<Notificaciones> { PantallaProvisional("10", "Notificaciones") }
        composable<CampanaPropuesta> { PantallaProvisional("11", "Campaña propuesta") }
        composable<CampanaMedida> { PantallaProvisional("12", "Campaña a medida") }
        composable<Perfil> { PantallaProvisional("13", "Perfil") }
        composable<Estadisticas> { PantallaProvisional("14", "Estadísticas") }
        composable<Calendario> { PantallaProvisional("15", "Calendario") }
    }
}
