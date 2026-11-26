package com.alanturing.society.feature.empezamos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.arranque.data.remote.CreateResult
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepository
import com.alanturing.society.feature.empezamos.data.remote.SearchResult
import com.alanturing.society.feature.empezamos.data.repository.PlaceRepository
import com.alanturing.society.feature.empezamos.model.Local
import com.alanturing.society.navigation.AccesoRoute
import com.alanturing.society.navigation.AnalisisRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Google no da más de 60 resultados por búsqueda (3 páginas de 20)
private const val MAX_RESULTS = 60

/**
 * Lógica de la pantalla 02 · Empezamos. Lanza la búsqueda, pide las páginas
 * siguientes y las fotos, y da de alta el local elegido en el modal M02.
 * Sobrevive a los giros de pantalla.
 */
@HiltViewModel
class EmpezamosViewModel @Inject constructor(
    private val placeRepository: PlaceRepository,
    private val restauranteRepository: RestauranteRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    // _estado es privado y modificable y solo viewmodel puede cambiarlo
    private val _estado = MutableStateFlow<EmpezamosUiState>(EmpezamosUiState.Inicial)
    // estado es público y solo lectura
    val estado: StateFlow<EmpezamosUiState> = _estado.asStateFlow()

    private val _confirm = MutableStateFlow<ConfirmPlaceState?>(null)
    val confirm: StateFlow<ConfirmPlaceState?> = _confirm.asStateFlow()

    // A dónde tiene que ir la pantalla: AnalisisRoute o AccesoRoute. null = quedarse
    private val _destino = MutableStateFlow<Any?>(null)
    val destino: StateFlow<Any?> = _destino.asStateFlow()

    private var ultimaBusqueda = "" // Para poder reintentar tras un error
    private var nextPageToken: String? = null
    private var busqueda: Job? = null
    private var masResultados: Job? = null

    // Si el alta salió bien pero el análisis no, al reintentar no se vuelve a crear el local
    private var createdPlaceId: String? = null
    private var createdRestauranteId: String? = null

    fun buscar(texto: String) {
        ultimaBusqueda = texto
        nextPageToken = null
        _estado.value = EmpezamosUiState.Cargando

        // si había otra búsqueda en marcha, ya no interesa
        busqueda?.cancel()
        masResultados?.cancel()
        // viewModelScope cancela la búsqueda si se cierra la pantalla
        busqueda = viewModelScope.launch {
            when (val resultado = placeRepository.search(texto)) {
                is SearchResult.Ok -> {
                    val locales = resultado.page.locales.take(MAX_RESULTS)
                    nextPageToken = resultado.page.nextPageToken
                    _estado.value =
                        if (locales.isEmpty()) EmpezamosUiState.Vacio
                        else EmpezamosUiState.ConDatos(locales, hayMasPaginas = quedanPaginas(locales.size))
                    cargarFotos(locales)
                }
                SearchResult.SessionExpired -> sesionCaducada()
                SearchResult.Error -> _estado.value = EmpezamosUiState.Error
            }
        }
    }

    fun reintentar() = buscar(ultimaBusqueda)

    fun verMas() {
        val actual = _estado.value as? EmpezamosUiState.ConDatos ?: return
        val token = nextPageToken ?: return
        if (actual.loadingMore) return

        _estado.value = actual.copy(loadingMore = true, loadMoreFailed = false)
        masResultados = viewModelScope.launch {
            when (val resultado = placeRepository.search(ultimaBusqueda, token)) {
                is SearchResult.Ok -> {
                    nextPageToken = resultado.page.nextPageToken
                    val nuevos = resultado.page.locales
                    actualizarDatos {
                        // distinctBy por si Google repite algún local entre páginas
                        val locales = (it.locales + nuevos).distinctBy { local -> local.id }.take(MAX_RESULTS)
                        it.copy(locales = locales, hayMasPaginas = quedanPaginas(locales.size), loadingMore = false)
                    }
                    cargarFotos(nuevos)
                }
                SearchResult.SessionExpired -> sesionCaducada()
                SearchResult.Error -> actualizarDatos { it.copy(loadingMore = false, loadMoreFailed = true) }
            }
        }
    }

    // Pide a la vez la primera foto de cada local y la pinta en cuanto llega
    private suspend fun cargarFotos(locales: List<Local>) = coroutineScope {
        locales.forEach { local ->
            val foto = local.photos.firstOrNull() ?: return@forEach
            launch {
                val url = placeRepository.photoUrl(foto.name) ?: return@launch
                actualizarDatos { it.copy(photoUrls = it.photoUrls + (local.id to url)) }
            }
        }
    }

    // Abre el modal M02 con el local de la tarjeta
    fun esMiLocal(local: Local) {
        _confirm.value = ConfirmPlaceState(local)
    }

    fun cerrarModal() {
        if (_confirm.value?.saving == true) return // no se cierra a mitad del alta
        _confirm.value = null
    }

    // Da de alta el local y lanza su análisis. Si todo va bien, la pantalla navega a S03
    fun confirmarLocal() {
        val modal = _confirm.value ?: return
        if (modal.saving) return
        _confirm.value = modal.copy(saving = true, error = null)

        viewModelScope.launch {
            val restauranteId = restauranteCreado(modal.local) ?: return@launch
            val analisisId = restauranteRepository.launchAnalysis(restauranteId)
            if (analisisId == null) {
                _confirm.value = modal.copy(saving = false, error = ConfirmError.CONNECTION)
            } else {
                _confirm.value = null
                _destino.value = AnalisisRoute(analisisId)
            }
        }
    }

    // Devuelve el id del local ya creado, o null si el alta falló (y deja el error en el modal)
    private suspend fun restauranteCreado(local: Local): String? {
        if (createdPlaceId == local.id) return createdRestauranteId

        return when (val resultado = restauranteRepository.createRestaurant(local.id)) {
            is CreateResult.Created -> {
                createdPlaceId = local.id
                createdRestauranteId = resultado.restauranteId
                resultado.restauranteId
            }
            CreateResult.Duplicate -> {
                _confirm.update { it?.copy(saving = false, error = ConfirmError.DUPLICATE) }
                null
            }
            CreateResult.SessionExpired -> {
                sesionCaducada()
                null
            }
            // Invalid no debería pasar: desde la búsqueda solo se manda el placeId
            CreateResult.Invalid, CreateResult.Error -> {
                _confirm.update { it?.copy(saving = false, error = ConfirmError.CONNECTION) }
                null
            }
        }
    }

    // La sesión murió: se borra el token y se vuelve a S01A
    private suspend fun sesionCaducada() {
        authRepository.cerrarSesion()
        _confirm.value = null
        _destino.value = AccesoRoute
    }

    // Evita que se dispare la navegación una segunda vez
    fun navegacionHecha() {
        _destino.value = null
    }

    private fun quedanPaginas(cuantos: Int) = nextPageToken != null && cuantos < MAX_RESULTS

    // Cambia el estado solo si sigue habiendo datos (puede haber empezado otra búsqueda)
    private fun actualizarDatos(cambio: (EmpezamosUiState.ConDatos) -> EmpezamosUiState.ConDatos) {
        _estado.update { if (it is EmpezamosUiState.ConDatos) cambio(it) else it }
    }
}
