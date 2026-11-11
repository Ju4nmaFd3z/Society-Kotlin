package com.alanturing.society.core.network

import javax.inject.Inject

class SaludRepositoryImpl @Inject constructor(
    private val remoteDataSource: SaludDataSource
) : SaludRepository {

    override suspend fun comprobarSalud(): Salud {
        return remoteDataSource.comprobarSalud()
    }
}
