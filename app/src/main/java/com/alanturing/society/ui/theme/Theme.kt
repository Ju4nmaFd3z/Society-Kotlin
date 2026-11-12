package com.alanturing.society.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Hemos quitado el color dinámico, para que la app use siempre su paleta y no la del fondo de pantalla
private val EsquemaSociety = lightColorScheme(
    primary = AzulInterfaz,
    onPrimary = Blanco,
    secondary = AmarilloInterfaz,
    onSecondary = TintaInterfaz,
    tertiary = Positivo,
    onTertiary = Blanco,
    error = Alerta,
    onError = Blanco,
    background = PapelInterfaz,
    onBackground = TintaInterfaz,
    surface = Blanco,
    onSurface = TintaInterfaz,
    surfaceVariant = Piedra,
    onSurfaceVariant = TextoSecundario,
    outline = TextoTerciario,
    outlineVariant = Piedra,

    surfaceContainerLowest = Blanco,
    surfaceContainerLow = Blanco,
    surfaceContainer = Blanco,
    surfaceContainerHigh = Blanco,
    surfaceContainerHighest = Blanco
)

@Composable
fun SocietyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaSociety,
        typography = Typography,
        content = content
    )
}