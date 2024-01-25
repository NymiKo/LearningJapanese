package com.example.peil.ui.screens.profile.data.model

data class ProfileResponseModel(
    val nickname: String,
    val avatar: String,
    val email: String = "",
    val progress: Float = 0.0F
) {
    fun toProfileModel() = ProfileModel(
        nickname = nickname,
        avatar = avatar,
        email = email,
        progress = progress
    )
}
