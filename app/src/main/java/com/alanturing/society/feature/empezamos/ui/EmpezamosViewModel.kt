package com.alanturing.society.feature.empezamos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.empezamos.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Lógica de la pantalla 02 · Empezamos. Lanza la búsqueda y guarda el estado actual
 * (inicial, cargando, vacío, error o con datos). Sobrevive a los giros de pantalla.
 */
@HiltViewModel
class EmpezamosViewModel @Inject constructor(
    private val repositorio: PlacesRepository // Hilt lo crea y lo pasa solo
) : ViewModel() {
    // _estado es privado y modificable y solo viewmodel puede cambiarlo
    private val _estado = MutableStateFlow<EmpezamosUiState>(EmpezamosUiState.Inicial)
    // estado es público y solo lectura
    val estado: StateFlow<EmpezamosUiState> = _estado.asStateFlow()

    private var ultimaBusqueda = "" // Para poder reintentar tras un error

    fun buscar(texto: String) {
        ultimaBusqueda = texto
        _estado.value = EmpezamosUiState.Cargando
        // viewModelScope cancela la búsqueda si se cierra la pantalla
        viewModelScope.launch {
            _estado.value = try {
                val locales = repositorio.buscar(texto)
                if (locales.isEmpty()) EmpezamosUiState.Vacio
                else EmpezamosUiState.ConDatos(locales)
            } catch (e: Exception) {
                EmpezamosUiState.Error
            }
        }
    }

    fun reintentar() = buscar(ultimaBusqueda)
}
