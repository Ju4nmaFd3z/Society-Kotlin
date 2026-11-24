package com.alanturing.society.feature.acceso.di

import com.alanturing.society.feature.acceso.data.local.SesionDataStore
import com.alanturing.society.feature.acceso.data.local.SesionLocalDataSource
import com.alanturing.society.feature.acceso.data.remote.AuthDataSource
import com.alanturing.society.feature.acceso.data.remote.AuthRemoteDataSource
import com.alanturing.society.feature.acceso.data.repository.AuthRepository
import com.alanturing.society.feature.acceso.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    @Singleton
    fun bindsAuthDataSource(ds: AuthRemoteDataSource): AuthDataSource

    @Binds
    @Singleton
    fun bindsAuthRepository(repo: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindsSesionLocalDataSource(ds: SesionDataStore): SesionLocalDataSource

}