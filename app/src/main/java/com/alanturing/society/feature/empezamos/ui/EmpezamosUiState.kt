package com.alanturing.society.feature.empezamos.ui

import com.alanturing.society.feature.empezamos.model.Local

/**
 * Estados posibles de la pantalla 02 · Empezamos. Solo puede estar en uno a la vez:
 * Inicial (aún no se ha buscado), Cargando, Vacio (sin resultados), Error (fallo
 * de red) y ConDatos (búsqueda realizada, con locales y si quedan más páginas).
 */
sealed interface EmpezamosUiState {
    data object Inicial : EmpezamosUiState
    data object Cargando : EmpezamosUiState
    data object Vacio : EmpezamosUiState
    data object Error : EmpezamosUiState
    data class ConDatos(
        val locales: List<Local>,
        val hayMasPaginas: Boolean = false,
        val photoUrls: Map<String, String> = emptyMap(), // id del local → URL de su foto, se van rellenando
        val loadingMore: Boolean = false,                // pidiendo la página siguiente
        val loadMoreFailed: Boolean = false              // falló «Ver más resultados»
    ) : EmpezamosUiState
}

// Modal M02 «¿Es tu local?». Si es null, el modal está cerrado
data class ConfirmPlaceState(
    val local: Local,
    val saving: Boolean = false,
    val error: ConfirmError? = null
)

enum class ConfirmError { DUPLICATE, CONNECTION }
