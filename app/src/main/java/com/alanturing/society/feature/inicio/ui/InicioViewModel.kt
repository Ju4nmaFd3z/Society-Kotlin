package com.alanturing.society.feature.inicio.ui

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.alanturing.society.navigation.Inicio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Lógica de la pantalla de inicio. Guarda el texto del buscador y el estado actual,
 * lanza la búsqueda al pulsar la lupa del teclado y pide la página siguiente cuando
 * el usuario llega al final de la lista. Sobrevive a los giros de pantalla.
 */

@HiltViewModel
class InicioViewModel @Inject constructor() : ViewModel() {
    // _estado es privado y modificable y solo viewmodel puede cambiarlo
    private val _estado = MutableStateFlow<InicioUiState>(InicioUiState.Inicial)
    // estado es público y solo lectura
    val estado: StateFlow<InicioUiState> = _estado.asStateFlow()
}