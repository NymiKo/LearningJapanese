package com.example.peil.ui.screens.settings.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val settingsService: SettingsService
): SettingsRepository {
    override suspend fun loadAvatar(file: File): NetworkResult<Unit> = withContext(ioDispatcher) {
        return@withContext handleApi {
            settingsService.loadAvatar(MultipartBody.Part.createFormData("avatar", file.name, file.asRequestBody()))
        }
    }
}