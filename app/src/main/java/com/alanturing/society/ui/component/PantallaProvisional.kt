package com.alanturing.society.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Un botón de la pantalla provisional que lleva a otra pantalla.
data class Destino(val texto: String, val onClick: () -> Unit)

// Pantalla de cartón piedra: muestra su número y su nombre, y un botón por cada pantalla
// a la que lleva en el flujo de Figma. Se sustituye por la real cuando esté implementada.
@Composable
fun PantallaProvisional(
    numero: String,
    nombre: String,
    modifier: Modifier = Modifier,
    destinos: List<Destino> = emptyList()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(numero, style = MaterialTheme.typography.labelLarge)
        Text(nombre, style = MaterialTheme.typography.headlineMedium)
        Text("Pantalla en construcción")
        destinos.forEach { destino ->
            Button(onClick = destino.onClick) {
                Text(destino.texto)
            }
        }
    }
}
