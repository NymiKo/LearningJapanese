package com.example.peil.ui.screens.settings.data

import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.profile.data.model.ProfileModel
import java.io.File

interface SettingsRepository {
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
    suspend fun getFullProfile(): NetworkResult<ProfileModel>

    suspend fun clearAllTablesInLocalDB()
}