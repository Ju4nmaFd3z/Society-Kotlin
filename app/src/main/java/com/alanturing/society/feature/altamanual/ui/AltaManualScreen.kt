package com.alanturing.society.feature.altamanual.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AltaManualScreen(
    onDatosGuardados: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("02M - Alta manual") // TODO: formulario con nombre, dirección, municipio, tipo, web e Instagram
        Button(onClick = onDatosGuardados) { Text("Guardar y analizar") } // TODO: dar de alta el local en la API y lanzar el análisis
    }
}
