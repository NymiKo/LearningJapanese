package com.example.peil.ui.screens.change_nickname.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeNicknameRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val changeNicknameService: ChangeNicknameService
): ChangeNicknameRepository {
    override suspend fun changeNickname(newNickname: String): NetworkResult<Unit> = withContext(ioDispatcher) {
        return@withContext handleApi { changeNicknameService.changeNickname(newNickname) }
    }
}