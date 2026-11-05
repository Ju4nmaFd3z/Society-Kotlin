package com.alanturing.society.feature.acceso.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccesoScreen(
    onSesionIniciada: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("01A - Acceso") // TODO: pestañas crear cuenta e iniciar sesión, con email, contraseña y términos
        Button(onClick = onSesionIniciada) { Text("Entrar") } // TODO: crear cuenta o iniciar sesión en la API y guardar la sesión
    }
}
