package com.alanturing.society.feature.inicio.ui

import com.alanturing.society.feature.inicio.model.Local

/**
 * Estados posibles de la pantalla de inicio. Solo puede estar en uno a la vez:
 * Inicial (aún no se ha buscado), Cargando, Vacio (sin resultados), Error (fallo
 * de red) y ConDatos (búsqueda realizada, con locales y si quedan más páginas).
 */
sealed interface InicioUiState {
    data object Inicial : InicioUiState
    data object Cargando : InicioUiState
    data object Vacio : InicioUiState
    data object Error : InicioUiState
    data class ConDatos(
        val locales: List<Local>,
        val hayMasPaginas: Boolean = false
    ) : InicioUiState
}