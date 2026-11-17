package com.alanturing.society.feature.acceso.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface SocietyAuthApi {

    @POST("auth/registro")
    suspend fun registro(@Body peticion: RegistroPeticion): Response<SesionResponse>

    @POST("auth/acceso")
    suspend fun acceso(@Body peticion: AccesoPeticion): Response<SesionResponse>

}