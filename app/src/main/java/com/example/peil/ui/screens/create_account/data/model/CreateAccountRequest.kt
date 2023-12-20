package com.example.peil.ui.screens.create_account.data.model

import com.google.gson.annotations.SerializedName

data class CreateAccountRequest(
    @SerializedName("email") val email: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("password") val password: String
)