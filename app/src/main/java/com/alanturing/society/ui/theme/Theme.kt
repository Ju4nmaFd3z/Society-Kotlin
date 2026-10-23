package com.alanturing.society.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Society solo tiene modo claro, según la identidad de marca
// Hemos quitado el color dinámico, para que la app use siempre su paleta y no la del fondo de pantalla
private val EsquemaSociety = lightColorScheme(
    primary = AzulSociety,
    onPrimary = Blanco,
    secondary = AmarilloSociety,
    onSecondary = Tinta,
    background = Papel,
    onBackground = Tinta,
    surface = Papel,
    onSurface = Tinta,
    surfaceVariant = Piedra,
    onSurfaceVariant = GrisTexto,
    outline = GrisTexto,
    outlineVariant = Piedra
)

@Composable
fun SocietyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaSociety,
        typography = Typography,
        content = content
    )
}