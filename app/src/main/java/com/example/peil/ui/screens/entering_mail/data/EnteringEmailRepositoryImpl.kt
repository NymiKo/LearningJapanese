package com.example.peil.ui.screens.entering_mail.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.entering_mail.data.model.EnteringEmailRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EnteringEmailRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val enteringEmailService: EnteringEmailService
): EnteringEmailRepository {
    override suspend fun sendEmail(email: String): NetworkResult<Int> = withContext(ioDispatcher) {
        return@withContext handleApi { enteringEmailService.sendEmail(EnteringEmailRequest(email)) }
    }
}