package com.example.peil.ui.screens.verification.data

import com.example.peil.ui.screens.verification.data.model.VerificationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface VerificationService {
    @POST("verification.php")
    suspend fun verificationUser(@Body verificationRequest: VerificationRequest): Response<String>

    @POST("verificationForgotPassword.php")
    suspend fun verificationUserForgotPassword(@Body verificationRequest: VerificationRequest): Response<Unit>
}