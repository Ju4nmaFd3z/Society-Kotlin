package com.alanturing.society.feature.empezamos.data.remote

import com.alanturing.society.feature.empezamos.model.PlacePage

// Lo que devuelve la búsqueda: una página de locales, o el motivo por el que no se pudo.
// Al ser sealed, el ViewModel está obligado a tratar los tres casos en su when
sealed interface SearchResult {
    data class Ok(val page: PlacePage) : SearchResult
    data object SessionExpired : SearchResult   // 401 incluso tras intentar renovar
    data object Error : SearchResult            // sin conexión, 400, 429, 5xx o respuesta ilegible
}

interface PlaceDataSource {
    // Sin pageToken pide la primera página; con el nextPageToken anterior, la siguiente
    suspend fun search(query: String, pageToken: String? = null): SearchResult

    // URL de la foto para pintarla con Coil, o null si no se pudo conseguir
    suspend fun photoUrl(name: String, maxWidthPx: Int): String?
}
