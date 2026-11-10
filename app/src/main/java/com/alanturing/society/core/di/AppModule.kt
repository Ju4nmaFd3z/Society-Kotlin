package com.alanturing.society.core.di

import com.alanturing.society.core.network.SaludDataSource
import com.alanturing.society.core.network.SaludRemoteDataSource
import com.alanturing.society.core.network.SaludRepository
import com.alanturing.society.core.network.SaludRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    // Une cada interfaz con la clase que la implementa
    @Binds
    @Singleton
    fun bindsSaludDataSource(ds: SaludRemoteDataSource): SaludDataSource

    @Binds
    @Singleton
    fun bindsSaludRepository(repo: SaludRepositoryImpl): SaludRepository
}
