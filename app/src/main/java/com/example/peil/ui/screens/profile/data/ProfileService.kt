package com.example.peil.ui.screens.profile.data

import com.example.peil.ui.screens.profile.data.model.ProfileResponseModel
import retrofit2.Response
import retrofit2.http.POST

interface ProfileService {
    @POST("profile.php")
    suspend fun getProfile(): Response<ProfileResponseModel>
}