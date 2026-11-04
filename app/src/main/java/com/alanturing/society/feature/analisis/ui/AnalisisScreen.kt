package com.alanturing.society.feature.analisis.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alanturing.society.ui.component.Destino
import com.alanturing.society.ui.component.PantallaProvisional

/**
 * Pantalla 03 · Análisis («Estudiando tu local»). Recibe el ID del local desde Empezamos.
 * De momento es cartón piedra: el contenido y sus estados están por definir.
 */
@Composable
fun AnalisisScreen(
    localId: String,
    modifier: Modifier = Modifier,
    onContinuar: () -> Unit = {}
) {
    PantallaProvisional(
        numero = "03",
        nombre = "Análisis de $localId",
        modifier = modifier,
        destinos = listOf(Destino("Avisarme cuando termine", onContinuar))
    )
}
