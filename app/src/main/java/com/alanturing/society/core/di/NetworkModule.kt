package com.alanturing.society.core.di

import com.alanturing.society.BuildConfig
import com.alanturing.society.core.network.SocietyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Construye Retrofit una sola vez para toda la app, con la URL de API_BASE
    @Provides
    @Singleton
    fun provideSocietyApi(): SocietyApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(SocietyApi::class.java)
    }
}
