package com.alanturing.society.feature.crearcontenidograph.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FotosLocalScreen(
    onGenerar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("08 - Fotos del local") // TODO: elegir fotos de la galería o la cámara y confirmar los derechos
        Button(onClick = onGenerar) { Text("Generar contenido") } // TODO: crear la pieza en la API con el borrador
    }
}
