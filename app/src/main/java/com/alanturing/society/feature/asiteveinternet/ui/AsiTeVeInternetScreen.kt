package com.alanturing.society.feature.asiteveinternet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AsiTeVeInternetScreen(
    restauranteId: String,
    onConfirmar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("04 - Así te ve internet")
        Text("Restaurante: $restauranteId") // TODO: mostrar la ficha real del restaurante
        Button(onClick = onConfirmar) { Text("Todo correcto, seguir") } // TODO: confirmar la ficha en la API.
    }
}