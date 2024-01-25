package com.example.peil.ui.screens.settings.data

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface SettingsService {
    @Multipart
    @POST("loadAvatar.php")
    suspend fun loadAvatar(@Part avatar: MultipartBody.Part): Response<Unit>
}