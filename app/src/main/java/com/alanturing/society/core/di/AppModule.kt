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
import com.alanturing.society.feature.arranque.data.remote.RestauranteFakeDataSource
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepository
import com.alanturing.society.feature.arranque.data.repository.RestauranteRepositoryImpl
import com.alanturing.society.feature.empezamos.data.remote.PlaceDataSource
import com.alanturing.society.feature.empezamos.data.remote.PlaceRemoteDataSource
import com.alanturing.society.feature.empezamos.data.repository.PlaceRepository
import com.alanturing.society.feature.empezamos.data.repository.PlaceRepositoryImpl

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

    // TODO: volver a RestauranteRemoteDataSource cuando la API tenga POST /restaurantes
    // y POST /restaurantes/{id}/analisis (SOCIETY-58 y SOCIETY-60)
    @Binds
    @Singleton
    fun bindsRestauranteDataSource(ds: RestauranteFakeDataSource): RestauranteDataSource

    @Binds
    @Singleton
    fun bindsRestauranteRepository(repo: RestauranteRepositoryImpl): RestauranteRepository

    @Binds
    @Singleton
    fun bindsPlaceDataSource(ds: PlaceRemoteDataSource): PlaceDataSource

    @Binds
    @Singleton
    fun bindsPlaceRepository(repo: PlaceRepositoryImpl): PlaceRepository
}
