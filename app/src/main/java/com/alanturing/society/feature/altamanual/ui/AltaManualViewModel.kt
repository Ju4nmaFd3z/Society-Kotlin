package com.alanturing.society.feature.altamanual.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.arranque.data.remote.CreateResult
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepository
import com.alanturing.society.feature.arranque.model.PlaceType
import com.alanturing.society.navigation.AccesoRoute
import com.alanturing.society.navigation.AnalisisRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Lógica de S02M · Alta manual. Guarda lo que se escribe, valida el formulario,
 * da de alta el local y lanza su análisis. Sobrevive a los giros de pantalla.
 */
@HiltViewModel
class AltaManualViewModel @Inject constructor(
    private val restauranteRepository: RestauranteRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _estado = MutableStateFlow(AltaManualUiState())
    val estado: StateFlow<AltaManualUiState> = _estado.asStateFlow()

    // A dónde tiene que ir la pantalla: AnalisisRoute o AccesoRoute. null = quedarse
    private val _destino = MutableStateFlow<Any?>(null)
    val destino: StateFlow<Any?> = _destino.asStateFlow()

    // Los errores no salen hasta que se pulsa «Guardar y analizar» la primera vez.
    // A partir de ahí se vuelven a comprobar con cada letra, así se ve cuándo se arreglan
    private var intentado = false

    // Si el alta salió bien pero el análisis no, al reintentar no se vuelve a crear el local
    private var restauranteCreadoId: String? = null

    fun onNameChange(texto: String) = cambiar { it.copy(name = texto.take(NAME_MAX)) }
    fun onTypeChange(tipo: PlaceType) = cambiar { it.copy(type = tipo) }
    fun onAddressChange(texto: String) = cambiar { it.copy(address = texto.take(ADDRESS_MAX)) }
    fun onTownChange(texto: String) = cambiar { it.copy(town = texto.take(TOWN_MAX)) }
    fun onWebChange(texto: String) = cambiar { it.copy(web = texto) }
    fun onInstagramChange(texto: String) = cambiar { it.copy(instagram = texto) }
    fun onManagerChange(marcada: Boolean) = cambiar { it.copy(isManager = marcada) }

    private fun cambiar(cambio: (ManualForm) -> ManualForm) {
        _estado.update {
            if (it.saving) return@update it // mientras se guarda, el formulario no cambia
            val form = cambio(it.form)
            it.copy(
                form = form,
                errors = if (intentado) form.validate() else FormErrors(),
                saveError = null
            )
        }
    }

    fun guardar() {
        val actual = _estado.value
        if (actual.saving) return

        intentado = true
        val errores = actual.form.validate()
        if (!errores.isEmpty) {
            _estado.value = actual.copy(errors = errores)
            return
        }

        _estado.value = actual.copy(errors = errores, saving = true, saveError = null)
        viewModelScope.launch {
            val restauranteId = restauranteCreadoId ?: crearRestaurante(actual.form) ?: return@launch
            val analisisId = restauranteRepository.launchAnalysis(restauranteId)
            if (analisisId == null) {
                terminarConError(SaveError.SERVER)
            } else {
                _destino.value = AnalisisRoute(analisisId)
            }
        }
    }

    // Devuelve el id del local creado, o null si el alta falló (y deja el error en pantalla)
    private suspend fun crearRestaurante(form: ManualForm): String? {
        return when (val resultado = restauranteRepository.createManualRestaurant(form.toManualRestaurant())) {
            is CreateResult.Created -> {
                restauranteCreadoId = resultado.restauranteId
                resultado.restauranteId
            }
            CreateResult.Duplicate -> {
                terminarConError(SaveError.DUPLICATE)
                null
            }
            CreateResult.Invalid -> {
                terminarConError(SaveError.INVALID)
                null
            }
            CreateResult.Error -> {
                terminarConError(SaveError.SERVER)
                null
            }
            CreateResult.SessionExpired -> {
                // La sesión murió: se borra el token y se vuelve a S01A
                authRepository.cerrarSesion()
                _destino.value = AccesoRoute
                null
            }
        }
    }

    private fun terminarConError(error: SaveError) {
        _estado.update { it.copy(saving = false, saveError = error) }
    }

    // Evita que se dispare la navegación una segunda vez. Si se vuelve atrás desde S03,
    // el formulario tiene que estar listo otra vez, no con el botón cargando
    fun navegacionHecha() {
        _destino.value = null
        _estado.update { it.copy(saving = false) }
    }
}
