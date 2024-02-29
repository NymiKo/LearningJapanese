package com.example.peil.ui.screens.new_password.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.new_password.data.model.ChangePasswordRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewPasswordRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val newPasswordService: NewPasswordService
): NewPasswordRepository {
    override suspend fun newPassword(idUser: Int, newPassword: String): NetworkResult<Unit> = withContext(ioDispatcher) {
        return@withContext handleApi { newPasswordService.changePassword(ChangePasswordRequest(idUser, newPassword)) }
    }
}