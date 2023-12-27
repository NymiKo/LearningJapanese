package com.example.peil.ui.screens.login.data

import com.example.peil.ui.screens.login.data.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login.php")
    suspend fun login(@Body loginRequest: LoginRequest): Response<String>
}