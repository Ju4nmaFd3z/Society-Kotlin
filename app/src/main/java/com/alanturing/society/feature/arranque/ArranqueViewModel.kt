package com.alanturing.society.feature.arranque

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.acceso.data.remote.exception.SessionExpiredException
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepository
import com.alanturing.society.navigation.AccesoRoute
import com.alanturing.society.navigation.BienvenidaRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Estados del arranque: decidiendo (el splash sigue visible) o ya con destino
sealed interface ArranqueUiState {
    data object Cargando : ArranqueUiState
    data class Listo(val ruta: Any) : ArranqueUiState // la ruta con la que arranca la navegación
}

@HiltViewModel
class ArranqueViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val restauranteRepository: RestauranteRepository
) : ViewModel() {

    private val _estado = MutableStateFlow<ArranqueUiState>(ArranqueUiState.Cargando)
    val estado: StateFlow<ArranqueUiState> = _estado.asStateFlow()

    init {
        viewModelScope.launch {
            _estado.value = ArranqueUiState.Listo(decidirDestino())
        }
    }

    private suspend fun decidirDestino(): Any {
        // Sin token guardado: S01
        if (!authRepository.haySesion()) return BienvenidaRoute

        return restauranteRepository.miRestaurante().fold(
            onSuccess = { it.destino() ?: AccesoRoute },
            onFailure = { error ->
                // La sesión murió: se borra el token. Fallo de red o servidor: el token se conserva
                if (error is SessionExpiredException) authRepository.cerrarSesion()
                AccesoRoute
            }
        )
    }
}
