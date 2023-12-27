package com.example.peil.ui.screens.login

import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class LoginScreenState(
    val email: InputState = InputState(type = InputType.EMAIL),
    val password: InputState = InputState(type = InputType.PASSWORD),
    val progress: Boolean = false,
    val formValid: Boolean
)
