package com.alanturing.society.feature.campanamedida.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampanaMedidaScreen(
    onPlanCreado: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("12 - Campaña a medida") // TODO: objetivo, producto, mensaje, fechas y formatos de la campaña
        Button(onClick = onPlanCreado) { Text("Crear plan de campaña") } // TODO: crear el plan de campaña en la API
    }
}
