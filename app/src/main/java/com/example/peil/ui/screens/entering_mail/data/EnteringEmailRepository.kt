package com.example.peil.ui.screens.entering_mail.data

import com.example.peil.data.NetworkResult

interface EnteringEmailRepository {
    suspend fun sendEmail(email: String): NetworkResult<Unit>
}