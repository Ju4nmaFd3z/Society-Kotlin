package com.alanturing.society.feature.acceso.data.repository

interface LoginRepository {

    suspend fun login(user:String,password:String):Boolean
}