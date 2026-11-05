package com.alanturing.society.feature.empezamos.data

import android.content.Context
import com.alanturing.society.feature.empezamos.data.dto.PlaceDto
import com.alanturing.society.feature.empezamos.data.dto.PlacesResponseDto
import com.alanturing.society.feature.empezamos.model.Local
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Punto único de acceso a los datos de locales para el ViewModel.
 * De momento lee los locales de ejemplo de assets/locales.json, en lugar de la API
 * de Society, y los traduce a objetos Local. El ViewModel no necesita saber de
 * dónde vienen los datos, así que al cambiar a la API solo cambia esta clase.
 */
class PlacesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val json = Json { ignoreUnknownKeys = true } // Ignora los campos del JSON que no usamos

    // Devuelve los locales cuyo nombre o dirección contienen el texto buscado
    suspend fun buscar(texto: String): List<Local> = withContext(Dispatchers.IO) {
        delay(500) // Simula el tiempo de respuesta de la red, para ver el estado Cargando

        // assets.open abre el archivo como un flujo de bytes. bufferedReader lo lee como texto,
        // y use { it.readText() } lee tod el texto de golpe y cierra el archivo al acabar.
        val contenido = context.assets.open("locales.json").bufferedReader().use { it.readText() }
        // Convierte el texto JSON en objetos Kotlin. El tipo entre <> le dice qué forma tiene:
        // busca en el JSON los campos de PlacesResponseDto y rellena cada propiedad por su nombre.
        val respuesta = json.decodeFromString<PlacesResponseDto>(contenido)

        respuesta.places
            .filter {
                it.displayName.text.contains(texto, ignoreCase = true) ||
                    it.formattedAddress.contains(texto, ignoreCase = true) // ignore hace que no importen las mayúsculas.
             }
            // map transforma cada elemento de la lista: cada PlaceDto (forma del JSON)
            // se convierte en un Local (lo que pinta la tarjeta) y devuelve la lista nueva.
            .map { it.toLocal() }
    }
}

// Traduce el local del JSON al modelo que usa la interfaz
private fun PlaceDto.toLocal() = Local(
    id = id,
    name = displayName.text,
    address = formattedAddress,
    rating = rating,
    userRatingCount = userRatingCount,
    photos = photos.map { it.name }
)
