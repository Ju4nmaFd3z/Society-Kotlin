package com.alanturing.society.core.di

import com.alanturing.society.core.network.SaludDataSource
import com.alanturing.society.core.network.SaludRemoteDataSource
import com.alanturing.society.core.network.SaludRepository
import com.alanturing.society.core.network.SaludRepositoryImpl
import com.alanturing.society.feature.acceso.data.local.SesionDataStore
import com.alanturing.society.feature.acceso.data.local.SesionLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.alanturing.society.feature.acceso.data.remote.AuthDataSource
import com.alanturing.society.feature.acceso.data.remote.AuthRemoteDataSource
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.acceso.data.repository.AuthRepositoryImpl
import com.alanturing.society.feature.arranque.data.remote.RestauranteDataSource
import com.alanturing.society.feature.arranque.data.remote.RestauranteRemoteDataSource
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepository
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepositoryImpl

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

    @Binds
    @Singleton
    fun bindsAuthDataSource(ds: AuthRemoteDataSource): AuthDataSource

    @Binds
    @Singleton
    fun bindsAuthRepository(repo: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsSesionLocalDataSource(ds: SesionDataStore): SesionLocalDataSource

    @Binds
    @Singleton
    fun bindsRestauranteDataSource(ds: RestauranteRemoteDataSource): RestauranteDataSource

    @Binds
    @Singleton
    fun bindsRestauranteRepository(repo: RestauranteRepositoryImpl): RestauranteRepository
}
