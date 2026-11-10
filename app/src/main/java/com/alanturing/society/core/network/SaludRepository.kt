package com.alanturing.society.core.network

interface SaludRepository {
    suspend fun comprobarSalud(): Salud
}
