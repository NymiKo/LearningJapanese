package com.example.peil.ui.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.login.data.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState(formValid = true))
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
                _state.value = state.value.copy(
                    progress = true
                )
                login()
            }
        }
    }

    private fun login() = viewModelScope.launch {
        delay(2000)
        _state.value = state.value.copy(
            progress = false
        )
//        when (val result = repository.login(state.value.email.text, state.value.password.text)) {
//            is NetworkResult.Error -> {
//
//            }
//
//            is NetworkResult.Success -> {
//
//            }
//        }
    }
}