package com.example.peil.ui.screens.login.data

import com.example.peil.data.NetworkResult

interface LoginRepository {
    suspend fun login(email: String, password: String): NetworkResult<String>
}