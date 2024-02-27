package com.example.peil.ui.screens.create_account.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.create_account.data.model.CreateAccountRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CreateAccountRepositoryImpl @Inject constructor(
    private val createAccountService: CreateAccountService
): CreateAccountRepository {
    override suspend fun createAccount(email: String, nickname: String, password: String): NetworkResult<Int> = withContext(Dispatchers.IO) {
        return@withContext handleApi { createAccountService.createAccount(CreateAccountRequest(email, nickname, password)) }
    }
}