package com.example.peil.ui.screens.new_password.state

import com.example.peil.R
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class NewPasswordScreenState(
    val newPassword: InputState = InputState(type = InputType.PASSWORD),
    val repeatNewPassword: InputState = InputState(type = InputType.PASSWORD),
    val progress: Boolean = false,
    val successChangePassword: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.unknown_error,
    val idUser: Int = 0
)
