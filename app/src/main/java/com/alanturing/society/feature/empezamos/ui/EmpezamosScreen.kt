package com.alanturing.society.feature.empezamos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

class EmpezamosScreen {

@Composable
fun EmpezamosScreen(
    onAnalisisLanzado: (analisisId: String) -> Unit,
    onAltManual: () -> Unit
) {
    Column { //TODO abc se sustituye por ViewModel lanzarAnalisis(restauranteId).
        Button(onClick = {onAnalisisLanzado("abc")}) { Text("Analizar") }
        TextButton(onClick = onAltManual ) { Text("Añadelo a mano") }
    }
}
}