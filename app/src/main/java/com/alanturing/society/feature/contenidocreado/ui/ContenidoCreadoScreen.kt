package com.alanturing.society.feature.contenidocreado.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContenidoCreadoScreen(
    piezaId: String,
    onPublicar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("09 - Contenido creado")
        Text("Pieza: $piezaId") // TODO: mostrar las diapositivas generadas y permitir regenerarlas
        Button(onClick = onPublicar) { Text("Descripción y hashtags") }
    }
}
