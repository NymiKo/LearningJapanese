package com.example.peil.ui.screens.profile.data

import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.profile.data.model.ProfileModel

interface ProfileRepository {
    suspend fun getProfile(): NetworkResult<ProfileModel>
}