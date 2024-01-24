package com.example.peil.ui.screens.change_nickname.state

import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType

data class ChangeNicknameState(
    val nickname: InputState = InputState(type = InputType.NICKNAME),
    val progress: Boolean = false,
    val formValid: Boolean,
    val successChangeNickname: Boolean = false
)
