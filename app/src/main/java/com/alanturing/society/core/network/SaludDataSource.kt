package com.alanturing.society.core.network

interface SaludDataSource {
    suspend fun comprobarSalud(): Salud
}
