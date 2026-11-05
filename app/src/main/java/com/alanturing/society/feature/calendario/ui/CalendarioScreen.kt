package com.alanturing.society.feature.calendario.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalendarioScreen(
    onPieza: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("15 - Calendario") // TODO: calendario del mes con las piezas de cada día
        Button(onClick = onPieza) { Text("Ver pieza") } // TODO: una por cada pieza del mes
    }
}
