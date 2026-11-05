package com.alanturing.society.feature.publicarcontenido.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PublicarContenidoScreen(
    piezaId: String,
    onPublicado: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("09P - Publicar contenido")
        Text("Pieza: $piezaId") // TODO: pie, hashtags y texto alternativo de la pieza
        Button(onClick = onPublicado) { Text("Publicar en Instagram") } // TODO: publicar por la API o descargar para hacerlo a mano
    }
}
