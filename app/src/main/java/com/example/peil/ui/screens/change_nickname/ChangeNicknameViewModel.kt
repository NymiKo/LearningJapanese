package com.example.peil.ui.screens.change_nickname

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.base_state.InputState
import com.example.peil.base_state.InputType
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.change_nickname.data.ChangeNicknameRepository
import com.example.peil.ui.screens.change_nickname.state.ChangeNicknameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeNicknameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ChangeNicknameRepository
): ViewModel() {
    private var oldNickname: String = savedStateHandle[nicknameKeyArg] ?: ""

    private val _state = mutableStateOf(ChangeNicknameState(formValid = true, nickname = InputState(text = oldNickname, type = InputType.NICKNAME)))
    val state: State<ChangeNicknameState> = _state

    fun enteringNickname(inputNickname: String) {
        _state.value = state.value.copy(nickname = state.value.nickname.copy(text = inputNickname), isNewNickname = inputNickname != oldNickname)
    }

    fun changeNickname() = viewModelScope.launch {
        when(repository.changeNickname(state.value.nickname.text)) {
            is NetworkResult.Error -> {
                _state.value = state.value.copy(successChangeNickname = false)
            }
            is NetworkResult.Success -> {
                _state.value = state.value.copy(successChangeNickname = true)
            }
        }
    }
}