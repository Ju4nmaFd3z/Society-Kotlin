package com.alanturing.society.feature.altamanual.ui

import com.alanturing.society.feature.arranque.model.ManualRestaurant
import com.alanturing.society.feature.arranque.model.PlaceType
import java.net.URI
import java.net.URISyntaxException

// Límites del contrato (AltaManualPeticion). El máximo se aplica al escribir: no deja pasar de ahí
const val NAME_MIN = 2
const val NAME_MAX = 80
const val ADDRESS_MIN = 3
const val ADDRESS_MAX = 120
const val TOWN_MIN = 2
const val TOWN_MAX = 60

// Instagram no se corta al escribir: la longitud (máximo 30) la comprueba la expresión regular
private val INSTAGRAM_PATTERN = Regex("^[a-z0-9._]{1,30}$")

// Lo que hay escrito en el formulario de S02M, tal cual lo teclea el usuario
data class ManualForm(
    val name: String = "",
    val type: PlaceType? = null,
    val address: String = "",
    val town: String = "",
    val web: String = "",
    val instagram: String = "",
    val isManager: Boolean = false // casilla «Declaro que gestiono este local»
)

// Por qué no vale un campo. La pantalla elige el texto de cada caso
sealed interface FieldError {
    data object Required : FieldError
    data class TooShort(val min: Int) : FieldError
    data object InvalidWeb : FieldError
    data object InvalidInstagram : FieldError
}

// Error de cada campo, o null si está bien
data class FormErrors(
    val name: FieldError? = null,
    val type: FieldError? = null,
    val address: FieldError? = null,
    val town: FieldError? = null,
    val web: FieldError? = null,
    val instagram: FieldError? = null,
    val isManager: FieldError? = null
) {
    val isEmpty: Boolean
        get() = listOf(name, type, address, town, web, instagram, isManager).all { it == null }
}

// Comprueba en el móvil las mismas reglas que la API, para no mandar nada que vaya a rechazar
fun ManualForm.validate() = FormErrors(
    name = checkLength(name, NAME_MIN),
    type = if (type == null) FieldError.Required else null,
    address = checkLength(address, ADDRESS_MIN),
    town = checkLength(town, TOWN_MIN),
    web = cleanWeb()?.let { if (isValidWeb(it)) null else FieldError.InvalidWeb },
    instagram = cleanInstagram()?.let { if (INSTAGRAM_PATTERN.matches(it)) null else FieldError.InvalidInstagram },
    isManager = if (isManager) null else FieldError.Required
)

// Pasa el formulario al modelo que se envía. Solo se llama si validate() no ha dado errores
fun ManualForm.toManualRestaurant() = ManualRestaurant(
    name = name.trim(),
    type = requireNotNull(type),
    address = address.trim(),
    town = town.trim(),
    web = cleanWeb(),
    instagram = cleanInstagram()
)

private fun checkLength(texto: String, min: Int): FieldError? = when {
    texto.isBlank() -> FieldError.Required
    texto.trim().length < min -> FieldError.TooShort(min)
    else -> null
}

// Campos opcionales: vacíos cuentan como no escritos (null)
private fun ManualForm.cleanWeb(): String? = web.trim().ifEmpty { null }

// Mucha gente escribe el usuario con @ o con mayúsculas: se le quita y se pasa a minúsculas
private fun ManualForm.cleanInstagram(): String? =
    instagram.trim().removePrefix("@").lowercase().ifEmpty { null }

// Tiene que empezar por https:// y tener un dominio con punto (https://boqueron.es)
private fun isValidWeb(web: String): Boolean {
    if (!web.startsWith("https://")) return false
    val host = try {
        URI(web).host
    } catch (e: URISyntaxException) {
        null
    }
    return host != null && host.contains('.')
}
