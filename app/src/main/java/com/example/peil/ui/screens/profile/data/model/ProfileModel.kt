package com.example.peil.ui.screens.profile.data.model

data class ProfileModel(
    val nickname: String,
    val avatar: String,
    val email: String = "",
    val progress: Float = 0.0F
)
