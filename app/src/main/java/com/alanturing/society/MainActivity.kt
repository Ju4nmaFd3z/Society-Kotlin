
/**
 * Punto de entrada de la app. Aplica el tema de Society y monta la navegación
 * (SocietyNavHost), que decide qué pantalla se ve en cada momento.
 */


package com.alanturing.society

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.alanturing.society.core.network.SaludRepository
import com.alanturing.society.navigation.SocietyNavHost
import com.alanturing.society.ui.theme.SocietyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint // Permite que Hilt inyecte el ViewModel en las pantallas
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var saludRepository: SaludRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Comprobación temporal de la capa de red: llama a GET /salud y escribe el resultado en el log.
        // Se quita cuando haya pantallas que usen la API de verdad.
        lifecycleScope.launch {
            val salud = saludRepository.comprobarSalud()
            Log.d("SocietyApi", "GET /salud -> ok=${salud.ok}")
        }

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
