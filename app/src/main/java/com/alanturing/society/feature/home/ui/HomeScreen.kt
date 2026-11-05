package com.alanturing.society.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onCrearContenido: () -> Unit,
    onOportunidad: () -> Unit,
    onPieza: () -> Unit,
    onCampanaMedida: () -> Unit,
    onCalendario: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("05 - Home") // TODO: nombre del local, valoración, oportunidades y piezas recientes de la API
        Button(onClick = onCrearContenido) { Text("+ Crear contenido") }
        Button(onClick = onOportunidad) { Text("Ver oportunidad") } // TODO: una tarjeta por oportunidad de la lista
        Button(onClick = onPieza) { Text("Ver pieza reciente") } // TODO: una miniatura por pieza reciente
        Button(onClick = onCampanaMedida) { Text("Campaña a tu medida") }
        // TODO: Calendario y Perfil irán en la barra inferior, junto a Estadísticas (próximamente)
        Button(onClick = onCalendario) { Text("Calendario") }
        Button(onClick = onPerfil) { Text("Perfil") }
    }
}
