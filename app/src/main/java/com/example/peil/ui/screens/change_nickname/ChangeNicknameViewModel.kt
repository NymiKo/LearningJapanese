package com.example.peil.ui.screens.change_nickname

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.change_nickname.data.ChangeNicknameRepository
import com.example.peil.ui.screens.change_nickname.state.ChangeNicknameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeNicknameViewModel @Inject constructor(
    private val repository: ChangeNicknameRepository
): ViewModel() {

    private val _state = mutableStateOf(ChangeNicknameState(formValid = true))
    val state: State<ChangeNicknameState> = _state

    fun enteringNickname(inputPassword: String) {
        _state.value = state.value.copy(nickname = state.value.nickname.copy(text = inputPassword))
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