package com.alanturing.society.core.di

import com.alanturing.society.core.network.AuthInterceptor
import com.alanturing.society.core.network.SocietyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // URL base de la API. Termina en barra para que Retrofit sume bien las rutas
    private const val API_BASE = "https://society-api-xi.vercel.app/api/v1/"

    // Cliente HTTP de la app: añade el Bearer y renueva la sesión ante un 401
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(cliente: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE)
            .client(cliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideSocietyApi(retrofit: Retrofit): SocietyApi =
        retrofit.create<SocietyApi>()


}