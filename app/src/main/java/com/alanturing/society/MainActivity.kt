
/**
 * Punto de entrada de la app. Aplica el tema de Society y monta la navegación
 * (SocietyNavHost), que decide qué pantalla se ve en cada momento.
 */


package com.alanturing.society

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.alanturing.society.core.network.SaludRepository
import com.alanturing.society.feature.arranque.ArranqueUiState
import com.alanturing.society.feature.arranque.ArranqueViewModel
import com.alanturing.society.navigation.SocietyNavHost
import com.alanturing.society.ui.theme.SocietyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint // Permite que Hilt inyecte el ViewModel en las pantallas
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var saludRepository: SaludRepository

    // Decide la pantalla de inicio. Va en la Activity porque se necesita antes de montar la navegación
    private val arranqueViewModel: ArranqueViewModel by viewModels()

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
                    // La navegación solo se monta cuando ya se sabe a dónde ir
                    val estado by arranqueViewModel.estado.collectAsStateWithLifecycle()
                    (estado as? ArranqueUiState.Listo)?.let {
                        SocietyNavHost(startDestination = it.ruta, modifier = screenModifier)
                    }
                }
            }
        }

        // Mantiene el splash del sistema en pantalla hasta que se sabe el destino
        val contenido: View = findViewById(android.R.id.content)
        contenido.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean =
                if (arranqueViewModel.estado.value is ArranqueUiState.Listo) {
                    contenido.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else {
                    false // No se dibuja: el splash sigue visible
                }
        })
    }
}
