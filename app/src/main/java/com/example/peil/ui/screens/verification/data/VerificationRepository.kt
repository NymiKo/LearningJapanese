package com.example.peil.ui.screens.verification.data

import com.example.peil.data.NetworkResult

interface VerificationRepository {
    suspend fun verificationUser(idUser: Int, code: String): NetworkResult<String>
    suspend fun verificationForgotPassword(idUser: Int, code: String): NetworkResult<Unit>
}