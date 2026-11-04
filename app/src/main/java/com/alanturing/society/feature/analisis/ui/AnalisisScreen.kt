package com.alanturing.society.feature.analisis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    analisisId: String,
    onTerminado: () -> Unit,
    onCancelar: () -> Unit,
) {
    Column {
        Text("Analizando: $analisisId")
        Button(onClick = onTerminado) { Text("Ver resultado") }
        TextButton(onClick = onCancelar) { Text("Cancelar") }
    }
}
