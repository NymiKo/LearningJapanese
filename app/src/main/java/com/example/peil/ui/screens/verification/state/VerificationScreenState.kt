package com.example.peil.ui.screens.verification.state

import com.example.peil.R
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class VerificationScreenState(
    val code: InputState = InputState(type = InputType.CODE),
    val progress: Boolean = false,
    val successVerification: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.unknown_error,
    val idUser: Int = 0,
    val token: String = "",
    val isForgotPassword: Boolean = false
)
