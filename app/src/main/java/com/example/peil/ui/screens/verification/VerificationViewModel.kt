package com.example.peil.ui.screens.verification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.login.state.LoginEvent
import com.example.peil.ui.screens.login.state.LoginScreenState
import com.example.peil.ui.screens.verification.data.VerificationRepository
import com.example.peil.ui.screens.verification.state.VerificationEvent
import com.example.peil.ui.screens.verification.state.VerificationScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val repository: VerificationRepository
) : ViewModel() {

    private val _state = mutableStateOf(VerificationScreenState(isError = false))
    val state: State<VerificationScreenState> = _state

    fun createEvent(event: VerificationEvent) {
        onEvent(event = event)
    }

    private fun onEvent(event: VerificationEvent) {
        when (event) {
            is VerificationEvent.EnteringCode -> {
                _state.value = state.value.copy(
                    code = state.value.code.copy(
                        text = event.code
                    )
                )
            }

            is VerificationEvent.OnVerification -> {
                if (!_state.value.code.isError) {
                    verification()
                }
            }

            is VerificationEvent.GetIdUser -> {
                _state.value = state.value.copy(
                    idUser = event.idUser
                )
            }
        }
    }

    private fun verification() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.verificationUser(state.value.idUser, state.value.code.text)) {
            is NetworkResult.Error -> {
                _state.value = state.value.copy(
                    successVerification = false,
                    progress = false,
                    isError = true,
                    errorMessage = R.string.email_or_password_incorrect
                )
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    successVerification = true,
                    isError = false,
                    token = result.data
                )
            }
        }
    }

    fun updateVerificationStatus() = viewModelScope.launch {
        _state.value = state.value.copy(successVerification = false)
    }
}