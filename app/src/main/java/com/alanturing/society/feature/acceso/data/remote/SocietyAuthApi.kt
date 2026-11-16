package com.alanturing.society.feature.acceso.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

//Se añade la clase porque con dos @body dentro de la funcion da error.
data class CredencialesDto(val email: String, val password: String)
interface SocietyAuthApi {

    @POST("auth/acceso/")
    suspend fun login(@Body credenciales: CredencialesDto): Response<Boolean>

}