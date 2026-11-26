package com.alanturing.society.feature.altamanual.ui

// Estado de la pantalla S02M: lo escrito, los errores de cada campo y cómo va el guardado
data class AltaManualUiState(
    val form: ManualForm = ManualForm(),
    val errors: FormErrors = FormErrors(), // vacíos hasta el primer «Guardar y analizar»
    val saving: Boolean = false,
    val saveError: SaveError? = null
)

enum class SaveError {
    DUPLICATE, // 409: el usuario ya tiene un local
    INVALID,   // la API ha rechazado algún dato
    SERVER     // sin conexión o fallo del servidor: se puede reintentar
}
