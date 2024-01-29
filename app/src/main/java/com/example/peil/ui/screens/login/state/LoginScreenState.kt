package com.example.peil.ui.screens.login.state

import com.example.peil.R
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class LoginScreenState(
    val email: InputState = InputState(type = InputType.EMAIL),
    val password: InputState = InputState(type = InputType.PASSWORD),
    val progress: Boolean = false,
    val successLogin: Boolean = false,
    val isError: Boolean,
    val errorMessage: Int = R.string.email_or_password_incorrect,
    val token: String = ""
)
