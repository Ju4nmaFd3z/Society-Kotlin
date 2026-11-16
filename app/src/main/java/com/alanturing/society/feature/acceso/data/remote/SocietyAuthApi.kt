package com.alanturing.society.feature.acceso.data.remote

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SocietyAuthApi {

    @POST("auth/acceso/")
    suspend fun login(@Body email:String, @Body password:String,): Response<Boolean>

}