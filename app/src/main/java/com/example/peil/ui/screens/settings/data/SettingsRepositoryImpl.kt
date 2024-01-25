package com.example.peil.ui.screens.settings.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.profile.data.model.ProfileModel
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

    override suspend fun getFullProfile(): NetworkResult<ProfileModel> = withContext(ioDispatcher) {
        return@withContext when(val result = handleApi { settingsService.getFullProfile() }) {
            is NetworkResult.Error -> {
                NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data.toProfileModel())
            }
        }
    }
}