package com.example.peil.ui.screens.profile.data.model

data class ProfileResponseModel(
    val nickname: String,
    val avatar: String,
    val progress: Float
) {
    fun toProfileModel() = ProfileModel(
        nickname = nickname,
        avatar = avatar,
        progress = progress
    )
}
