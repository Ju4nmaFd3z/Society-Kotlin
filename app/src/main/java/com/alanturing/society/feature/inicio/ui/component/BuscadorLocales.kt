package com.alanturing.society.feature.inicio.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun BuscadorLocales(
    texto: String,
    cambioTexto: (String) -> Unit, // Función que recibe un string y no devuelve nada
    buscar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val puedeBuscar = texto.isNotBlank() // isNotBlank sirve para saber si hay contenido para buscar ("   " = false)
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, // Alinear verticalmente
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Alinear horizontalmente con 8.dp de espacio a su alrededor
    ) {
        OutlinedTextField( // Igual que text field pero con borde alrededor
            value = texto,
            onValueChange = cambioTexto,
            modifier = Modifier.weight(1f), // Ocupa el ancho que deja el botón
            singleLine = true, // El texto ocupa solo una línea
            label =  { Text("Buscar local") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), // imeAction es el botón de acción, el botón de la lupa del teclado del móvil
            keyboardActions = KeyboardActions(
                onSearch = { if (puedeBuscar) buscar(texto.trim())} // trim para quitar los espacios sobrantes
            )
        )
        Button(
            onClick = { buscar(texto.trim()) },
            enabled = puedeBuscar // Desactivado si no hay texto
        ) {
            Text("Buscar")
            // TODO: Añadir icono de lupa.
        }
    }
}