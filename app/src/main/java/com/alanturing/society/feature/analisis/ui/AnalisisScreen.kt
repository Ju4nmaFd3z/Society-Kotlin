package com.alanturing.society.feature.analisis.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Pantalla 02 · Análisis, hecha en Compose. Es el destino de la navegación desde
 * Inicio, al pulsar Continuar en un local o desde el buscador de página web, y
 * desde aquí empieza el análisis.
 * Pendiente: definir su contenido y sus estados (ver nota de diseño).
 */


// composable para que aparezca algo en pantalla.
@Composable
fun AnalisisScreen(localId: String, modifier: Modifier = Modifier) {
    Text("Análisis de $localId", modifier = modifier)
}

