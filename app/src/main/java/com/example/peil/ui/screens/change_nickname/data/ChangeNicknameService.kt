package com.example.peil.ui.screens.change_nickname.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChangeNicknameService {
    @GET("changeNickname.php")
    suspend fun changeNickname(@Query("nickname") nickname: String): Response<Unit>
}