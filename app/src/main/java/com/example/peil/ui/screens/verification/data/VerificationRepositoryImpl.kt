package com.example.peil.ui.screens.verification.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.verification.data.model.VerificationRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VerificationRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val verificationService: VerificationService
): VerificationRepository {
    override suspend fun verificationUser(idUser: Int, code: String): NetworkResult<String> = withContext(ioDispatcher) {
        return@withContext handleApi { verificationService.verificationUser(VerificationRequest(idUser, code)) }
    }
}