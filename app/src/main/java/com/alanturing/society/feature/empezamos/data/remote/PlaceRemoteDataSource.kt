package com.alanturing.society.feature.empezamos.data.remote

import com.alanturing.society.core.network.SocietyApi
import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject

// Implementación real: llama a la API de Society, que es la que habla con Google (D-05)
class PlaceRemoteDataSource @Inject constructor(
    private val api: SocietyApi
) : PlaceDataSource {

    override suspend fun search(query: String, pageToken: String?): SearchResult {
        return try {
            val respuesta = api.searchPlaces(query, pageToken) // el interceptor pone el token y renueva si hace falta
            val pagina = respuesta.body()?.toModel()
            when {
                respuesta.isSuccessful && pagina != null -> SearchResult.Ok(pagina)
                respuesta.code() == 401 -> SearchResult.SessionExpired
                else -> SearchResult.Error
            }
        } catch (e: IOException) {
            // Sin conexión o sin llegar al servidor
            SearchResult.Error
        } catch (e: JsonParseException) {
            // La API respondió con algo que no se puede leer
            SearchResult.Error
        }
    }

    override suspend fun photoUrl(name: String, maxWidthPx: Int): String? {
        return try {
            val respuesta = api.placePhoto(name, maxWidthPx)
            if (respuesta.isSuccessful) respuesta.body()?.photoUri else null
        } catch (e: IOException) {
            null
        } catch (e: JsonParseException) {
            null
        }
    }
}
