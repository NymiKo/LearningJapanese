package com.example.peil.ui.screens.create_account

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import com.example.peil.ui.screens.create_account.state.CreateAccountEvent
import com.example.peil.ui.screens.create_account.state.CreateAccountScreenState
import com.example.peil.ui.screens.login.state.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val repository: CreateAccountRepository
) : ViewModel() {

    private val _state = mutableStateOf(CreateAccountScreenState(isError = false))
    val state: State<CreateAccountScreenState> = _state

    fun createEvent(event: CreateAccountEvent) {
        onEvent(event = event)
    }

    private fun onEvent(event: CreateAccountEvent) {
        when (event) {
            is CreateAccountEvent.EnteringEmail -> {
                _state.value = state.value.copy(
                    email = state.value.email.copy(
                        text = event.value
                    )
                )
            }

            is CreateAccountEvent.EnteringPassword -> {
                _state.value = state.value.copy(
                    password = state.value.password.copy(
                        text = event.value
                    )
                )
            }

            is CreateAccountEvent.EnteringNickname -> {
                _state.value = state.value.copy(
                    nickname = state.value.nickname.copy(
                        text = event.value
                    )
                )
            }

            is CreateAccountEvent.OnCreateAccount -> {
                checkValidEmail(_state.value.email.text)
                checkValidNickname(_state.value.nickname.text)
                checkValidPassword(_state.value.password.text)
                if (!_state.value.email.isError && !_state.value.nickname.isError && !_state.value.password.isError) {
                    createAccount()
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

    private fun checkValidNickname(nickname: String) {
        if (nickname.isEmpty()) {
            _state.value = state.value.copy(
                nickname = state.value.nickname.copy(
                    isError = true
                ),
                isError = true,
                errorMessage = R.string.nickname_empty
            )
        } else {
            _state.value = state.value.copy(
                nickname = state.value.nickname.copy(
                    isError = false
                )
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

    fun updateStateDialog() = viewModelScope.launch {
        _state.value = state.value.copy(isOpenHaveAccountDialog = false)
    }

    fun updateStateVerificationCode() = viewModelScope.launch {
        _state.value = state.value.copy(successCreateAccount = false)
    }

    private fun createAccount() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.createAccount(
            _state.value.email.text,
            _state.value.nickname.text,
            _state.value.password.text
        )) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    isError = false,
                    successCreateAccount = true,
                    idUser = result.data
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

            400 -> {
                _state.value = state.value.copy(
                    progress = false,
                    isError = false,
                    isOpenHaveAccountDialog = true,
                )
            }

            else -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.unknown_error,
            )
        }
    }
}