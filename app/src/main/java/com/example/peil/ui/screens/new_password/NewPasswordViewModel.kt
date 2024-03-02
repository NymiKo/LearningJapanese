package com.example.peil.ui.screens.new_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.new_password.data.NewPasswordRepository
import com.example.peil.ui.screens.new_password.navigation.idUserNewPasswordArg
import com.example.peil.ui.screens.new_password.state.NewPasswordEvent
import com.example.peil.ui.screens.new_password.state.NewPasswordScreenState
import com.example.peil.ui.screens.verification.state.VerificationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: NewPasswordRepository
): ViewModel() {

    private val _state = mutableStateOf(NewPasswordScreenState(idUser = savedStateHandle[idUserNewPasswordArg] ?: 0))
    val state: State<NewPasswordScreenState> = _state

    fun createEvent(event: NewPasswordEvent) {
        onEvent(event = event)
    }

    private fun onEvent(event: NewPasswordEvent) {
        when (event) {
            is NewPasswordEvent.EnteringNewPassword -> {
                _state.value = state.value.copy(
                    newPassword = state.value.newPassword.copy(
                        text = event.value
                    )
                )
            }

            is NewPasswordEvent.EnteringRepeatNewPassword -> {
                _state.value = state.value.copy(
                    repeatNewPassword = state.value.repeatNewPassword.copy(
                        text = event.value
                    )
                )
            }

            is NewPasswordEvent.ChangePassword -> {
                checkValidRepeatNewPassword(_state.value.repeatNewPassword.text)
                checkValidPassword(_state.value.newPassword.text)
                if (!_state.value.newPassword.isError && !_state.value.repeatNewPassword.isError) {
                    changePassword()
                }
            }
        }
    }

    private fun checkValidPassword(password: String) {
        if (password.length < 6) {
            _state.value = state.value.copy(
                newPassword = state.value.newPassword.copy(
                    isError = true
                ),
                isError = true,
                errorMessage = R.string.password_incorrect
            )
        } else {
            _state.value = state.value.copy(
                newPassword = state.value.newPassword.copy(
                    isError = false
                )
            )
        }
    }

    private fun checkValidRepeatNewPassword(password: String) {
        if (_state.value.newPassword.text != password) {
            _state.value = state.value.copy(
                repeatNewPassword = state.value.repeatNewPassword.copy(
                    isError = true
                ),
                isError = true,
                errorMessage = R.string.passwords_do_not_match
            )
        } else {
            _state.value = state.value.copy(
                repeatNewPassword = state.value.repeatNewPassword.copy(
                    isError = false
                )
            )
        }
    }

    private fun changePassword() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.newPassword(_state.value.idUser, state.value.newPassword.text)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    successChangePassword = true,
                    isError = false
                )
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.check_your_internet_connection,
            )

            else -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.unknown_error,
            )
        }
    }

    fun updateStatusNewPassword() = viewModelScope.launch {
        _state.value = state.value.copy(successChangePassword = false)
    }
}