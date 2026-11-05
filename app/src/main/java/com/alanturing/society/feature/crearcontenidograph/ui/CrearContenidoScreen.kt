package com.alanturing.society.feature.crearcontenidograph.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CrearContenidoScreen(
    onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("06 - Crear contenido") // TODO: formato, idea, tipo y condiciones. Precargar idea y tipo si llega campanaId
        Button(onClick = onSiguiente) { Text("Siguiente") }
    }
}
