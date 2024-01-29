package com.example.peil.ui.screens.create_account.state

import com.example.peil.R
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class CreateAccountScreenState(
    val email: InputState = InputState(type = InputType.EMAIL),
    val nickname: InputState = InputState(type = InputType.NICKNAME),
    val password: InputState = InputState(type = InputType.PASSWORD),
    val progress: Boolean = false,
    val successCreateAccount: Boolean = false,
    val isError: Boolean,
    val errorMessage: Int = R.string.error_create_account,
    val isOpenHaveAccountDialog: Boolean = false,
    val token: String = ""
)
