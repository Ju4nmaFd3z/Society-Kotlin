package com.alanturing.society.feature.crearcontenidograph.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EleccionEstiloScreen(
    onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("07 - Elección de estilo") // TODO: catálogo de estilos y subir una imagen de referencia
        Button(onClick = onSiguiente) { Text("Siguiente") }
    }
}
