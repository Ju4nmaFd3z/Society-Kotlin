package com.alanturing.society.feature.empezamos.data.repository

import com.alanturing.society.feature.empezamos.data.remote.PlaceDataSource
import com.alanturing.society.feature.empezamos.data.remote.SearchResult
import javax.inject.Inject

// Ancho con el que se piden las fotos: de sobra para el ancho de una tarjeta
private const val PHOTO_WIDTH_PX = 800

class PlaceRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlaceDataSource
) : PlaceRepository {

    // Las URL de las fotos se guardan en memoria para no pedirlas otra vez
    // al volver a buscar lo mismo. No se guardan en disco: caducan (D-06)
    private val photoUrls = mutableMapOf<String, String>()

    override suspend fun search(query: String, pageToken: String?): SearchResult =
        remoteDataSource.search(query, pageToken)

    override suspend fun photoUrl(name: String): String? {
        photoUrls[name]?.let { return it }
        val url = remoteDataSource.photoUrl(name, PHOTO_WIDTH_PX) ?: return null
        photoUrls[name] = url
        return url
    }
}
