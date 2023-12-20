package com.example.peil.ui.screens.create_account.data

import com.example.peil.ui.screens.create_account.data.model.CreateAccountRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateAccountService {
    @POST("registration.php")
    suspend fun createAccount(@Body createAccountRequest: CreateAccountRequest): Response<String>
}