package com.alanturing.society.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alanturing.society.ui.theme.AmarilloInterfaz
import com.alanturing.society.ui.theme.TintaInterfaz

/**
 * Botón amarillo de Society: avisa con [onClick] y la pantalla decide qué ocurre.
 *
 * @param texto    texto que muestra el botón
 * @param onClick  qué hacer al pulsarlo
 * @param modifier para colocarlo (ancho, padding, posición...) desde la pantalla que lo usa
 * @param enabled  si es false el botón se ve apagado y no responde a la pulsación
 */
@Composable
fun BotonCristal(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(54.dp),
        enabled = enabled,
        shape = RoundedCornerShape(27.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AmarilloInterfaz,
            contentColor = TintaInterfaz
        )
    ) {
        Text(texto, style = MaterialTheme.typography.labelLarge)
    }
}