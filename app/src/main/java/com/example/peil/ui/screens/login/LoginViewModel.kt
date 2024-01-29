package com.example.peil.ui.screens.login

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.login.data.LoginRepository
import com.example.peil.ui.screens.login.state.LoginEvent
import com.example.peil.ui.screens.login.state.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState(isError = false))
    val state: State<LoginScreenState> = _state

    fun createEvent(event: LoginEvent) {
        onEvent(event = event)
    }

    private fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteringEmail -> {
                _state.value = state.value.copy(
                    email = state.value.email.copy(
                        text = event.value
                    )
                )
            }

            is LoginEvent.EnteringPassword -> {
                _state.value = state.value.copy(
                    password = state.value.password.copy(
                        text = event.value
                    )
                )
            }

            is LoginEvent.OnLogin -> {
                checkValidEmail(_state.value.email.text)
                checkValidPassword(_state.value.password.text)
                if (!_state.value.email.isError && !_state.value.password.isError) {
                    login()
                }
            }
        }
    }

    private fun checkValidEmail(email: String) {
        if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = state.value.copy(
                email = state.value.email.copy(
                    isError = false
                )
            )
        } else {
            _state.value = state.value.copy(
                email = state.value.email.copy(
                    isError = true
                ),
                isError = true,
                errorMessage = R.string.email_incorrect
            )
        }
    }

    private fun checkValidPassword(password: String) {
        if (password.length < 6) {
            _state.value = state.value.copy(
                password = state.value.password.copy(
                    isError = true
                ),
                isError = true,
                errorMessage = R.string.password_incorrect
            )
        } else {
            _state.value = state.value.copy(
                password = state.value.password.copy(
                    isError = false
                )
            )
        }
    }

    private fun login() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.login(state.value.email.text, state.value.password.text)) {
            is NetworkResult.Error -> {
                _state.value = state.value.copy(
                    successLogin = false,
                    progress = false,
                    isError = true,
                    errorMessage = R.string.email_or_password_incorrect
                )
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    successLogin = true,
                    isError = false,
                    token = result.data
                )
            }
        }
    }
}