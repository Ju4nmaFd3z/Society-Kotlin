
/**
 * Punto de entrada de la app. Aplica el tema de Society y monta la navegación
 * (SocietyNavHost), que decide qué pantalla se ve en cada momento.
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
import com.alanturing.society.navigation.SocietyNavHost
import com.alanturing.society.ui.theme.SocietyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Permite que Hilt inyecte el ViewModel en las pantallas
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SocietyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val screenModifier = Modifier.fillMaxSize().padding(innerPadding)
                    SocietyNavHost(modifier = screenModifier)
                }
            }
        }
    }
}
