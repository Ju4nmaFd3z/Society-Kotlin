package com.alanturing.society.feature.bienvenida.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BienvenidaScreen(
    onDescubrir: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("01 - Bienvenida") // TODO: título «Ideas que llenan tu local», mascota y las 3 ventajas
        Button(onClick = onDescubrir) { Text("Descubre cómo funciona") }
    }
}
