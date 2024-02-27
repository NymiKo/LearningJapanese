package com.example.peil.ui.screens.create_account.data

import com.example.peil.data.NetworkResult

interface CreateAccountRepository {
    suspend fun createAccount(email: String, nickname: String, password: String): NetworkResult<Int>
}