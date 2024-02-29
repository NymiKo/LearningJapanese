package com.example.peil.ui.screens.entering_mail.state

import com.example.peil.R
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class EnteringEmailScreenState(
    val email: InputState = InputState(type = InputType.EMAIL),
    val progress: Boolean = false,
    val correctEmail: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: Int = R.string.unknown_error,
    val idUser: Int = 0
)