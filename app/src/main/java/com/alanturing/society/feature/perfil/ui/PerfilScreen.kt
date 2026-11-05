package com.alanturing.society.feature.perfil.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PerfilScreen(
    onDatosLocal: () -> Unit,
    onCerrarSesion: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("13 - Perfil") // TODO: datos de la cuenta y conectar o desconectar Instagram
        Button(onClick = onDatosLocal) { Text("Datos del local") }
        Button(onClick = onCerrarSesion) { Text("Cerrar sesión") } // TODO: pedir confirmación y borrar la sesión guardada
    }
}
