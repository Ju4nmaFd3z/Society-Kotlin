package com.alanturing.society.feature.campanapropuesta.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampanaPropuestaScreen(
    oportunidadId: String,
    onCrearCampana: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("11 - Campaña propuesta")
        Text("Oportunidad: $oportunidadId") // TODO: mostrar el detalle de la oportunidad, con Guardar y Descartar
        Button(onClick = onCrearCampana) { Text("Crear campaña") } // TODO: crear la campaña en la API
    }
}
