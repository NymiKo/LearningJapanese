package com.example.peil.ui.screens.login.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.login.data.model.LoginRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val loginService: LoginService
): LoginRepository {
    override suspend fun login(email: String, password: String): NetworkResult<String> = withContext(ioDispatcher) {
        return@withContext handleApi { loginService.login(LoginRequest(email, password)) }
    }
}