package com.example.peil.ui.screens.entering_mail

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.entering_mail.data.EnteringEmailRepository
import com.example.peil.ui.screens.entering_mail.state.EnteringEmailEvent
import com.example.peil.ui.screens.entering_mail.state.EnteringEmailScreenState
import com.example.peil.ui.screens.login.state.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteringEmailViewModel @Inject constructor(
    private val repository: EnteringEmailRepository
): ViewModel() {
    private val _state = mutableStateOf(EnteringEmailScreenState())
    val state: State<EnteringEmailScreenState> = _state

    fun createEvent(event: EnteringEmailEvent) {
        onEvent(event = event)
    }

    private fun onEvent(event: EnteringEmailEvent) {
        when (event) {
            is EnteringEmailEvent.EnteringEmail -> {
                _state.value = state.value.copy(
                    email = state.value.email.copy(
                        text = event.email
                    )
                )
            }

            is EnteringEmailEvent.SendingEmail -> {
                checkValidEmail(_state.value.email.text)
                if (!_state.value.email.isError) {
                    sendingEmail()
                }
            }
        }
    }

    private fun sendingEmail() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.sendEmail(state.value.email.text)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    correctEmail = true,
                    isError = false
                )
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

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.check_your_internet_connection,
            )

            404 -> {
                _state.value = state.value.copy(
                    progress = false,
                    isError = true,
                    errorMessage = R.string.error_no_account_with_email
                )
            }

            else -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.unknown_error,
            )
        }
    }

    fun updateCorrectEmailStatus() = viewModelScope.launch {
        _state.value = state.value.copy(correctEmail = false)
    }
}