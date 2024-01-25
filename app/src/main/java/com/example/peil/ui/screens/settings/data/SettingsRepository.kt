package com.example.peil.ui.screens.settings.data

import com.example.peil.data.NetworkResult
import java.io.File

interface SettingsRepository {
    suspend fun loadAvatar(file: File): NetworkResult<Unit>
}