package com.alanturing.society.feature.empezamos.data.repository

import com.alanturing.society.feature.empezamos.data.remote.SearchResult

interface PlaceRepository {
    suspend fun search(query: String, pageToken: String? = null): SearchResult

    suspend fun photoUrl(name: String): String?
}
