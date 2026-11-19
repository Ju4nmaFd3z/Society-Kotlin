package com.alanturing.society.feature.acceso.data.local

import com.alanturing.society.feature.acceso.model.Sesion
import kotlinx.coroutines.flow.Flow

// Sesión guardada en el móvil (token)
interface SesionLocalDataSource {

    // Lectura:emite la sesión guardada, o null si no hay
    val sesion: Flow<Sesion?>

    // Escritura
    suspend fun guardar(sesion: Sesion)

    //borra los tokens
    suspend fun borrar()
}