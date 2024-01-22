package com.example.peil.ui.screens.profile.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.profile.data.model.ProfileModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val profileService: ProfileService
): ProfileRepository {
    override suspend fun getProfile(): NetworkResult<ProfileModel> = withContext(ioDispatcher) {
        return@withContext when(val result = handleApi { profileService.getProfile() }) {
            is NetworkResult.Error -> {
                NetworkResult.Error(result.code)
            }
            is NetworkResult.Success -> {
                NetworkResult.Success(result.data.toProfileModel())
            }
        }
    }
}