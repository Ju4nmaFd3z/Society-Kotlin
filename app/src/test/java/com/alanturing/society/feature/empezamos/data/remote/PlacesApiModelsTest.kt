package com.alanturing.society.feature.empezamos.data.remote

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

// Comprueba que una respuesta de /places/search con la forma del contrato se lee bien con Gson,
// también cuando faltan los campos opcionales
class PlacesApiModelsTest {

    private val gson = Gson()

    private fun leer(json: String) = gson.fromJson(json, SearchResponse::class.java).toModel()

    @Test
    fun `lee un local completo con su foto y autor`() {
        val pagina = leer(
            """
            {"resultados":[{"placeId":"abc","nombre":"Bar El Boquerón Dorado","direccion":"Calle Larios 12",
              "valoracion":4.6,"numResenas":1280,"googleMapsUri":"https://maps.google.com/?cid=1",
              "fotos":[{"name":"places/abc/photos/1","atribuciones":[{"nombre":"Ana López"}]}]}],
             "nextPageToken":"pagina2"}
            """
        )

        val local = pagina.locales.single()
        assertEquals("abc", local.id)
        assertEquals("4.6", local.ratingText?.replace(',', '.')) // en español sale «4,6»
        assertEquals("Ana López", local.photos.single().authors.single())
        assertEquals("pagina2", pagina.nextPageToken)
    }

    @Test
    fun `un local sin reseñas ni fotos no rompe la lectura`() {
        val local = leer("""{"resultados":[{"placeId":"abc","nombre":"Bar nuevo"}]}""").locales.single()

        assertTrue(local.photos.isEmpty())
        assertNull(local.ratingText)
        assertEquals("", local.address)
    }

    @Test
    fun `descarta los locales sin id o sin nombre`() {
        val pagina = leer("""{"resultados":[{"nombre":"Sin id"},{"placeId":"x"},{"placeId":"y","nombre":""},{"placeId":"ok","nombre":"Bueno"}]}""")

        assertEquals(listOf("ok"), pagina.locales.map { it.id })
    }

    @Test
    fun `sin resultados ni token es una página vacía y la última`() {
        val pagina = leer("""{"resultados":[]}""")

        assertTrue(pagina.locales.isEmpty())
        assertNull(pagina.nextPageToken)
    }
}
