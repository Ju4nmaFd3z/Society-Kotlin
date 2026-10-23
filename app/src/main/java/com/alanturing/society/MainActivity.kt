
/**
 * Punto de entrada de la app. Aplica el tema de Society y muestra la primera
 * pantalla, InicioScreen. Cuando haya más pantallas, aquí se montará la navegación.
 */


package com.alanturing.society

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.alanturing.society.feature.inicio.ui.InicioScreen
import com.alanturing.society.feature.inicio.ui.InicioUiState
import com.alanturing.society.ui.theme.SocietyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SocietyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val screenModifier = Modifier.fillMaxSize().padding(innerPadding)
                    InicioScreen(
                        estado = InicioUiState.Inicial,
                        modifier = screenModifier,
                    )
                }
            }
        }
    }
}
