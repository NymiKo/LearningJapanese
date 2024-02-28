package com.example.peil.ui.screens.entering_mail.data

import com.example.peil.ui.screens.entering_mail.data.model.EnteringEmailRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EnteringEmailService {
    @POST("forgotPassword.php")
    suspend fun sendEmail(@Body enteringEmailRequest: EnteringEmailRequest): Response<Unit>
}