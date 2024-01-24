package com.example.peil.ui.screens.change_nickname.data

import com.example.peil.data.NetworkResult

interface ChangeNicknameRepository {
    suspend fun changeNickname(newNickname: String): NetworkResult<Unit>
}