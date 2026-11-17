package com.alanturing.society.feature.acceso.data.repository

interface AuthRepository {

    suspend fun login(email:String,password:String):Boolean
    suspend fun registro(email: String, password: String): ResultadoAcceso
}