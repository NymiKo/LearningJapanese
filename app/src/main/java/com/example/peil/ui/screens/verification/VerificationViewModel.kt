package com.example.peil.ui.screens.verification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.verification.data.VerificationRepository
import com.example.peil.ui.screens.verification.navigation.idUserVerificationKeyArg
import com.example.peil.ui.screens.verification.navigation.isForgotPasswordArg
import com.example.peil.ui.screens.verification.state.VerificationEvent
import com.example.peil.ui.screens.verification.state.VerificationScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: VerificationRepository
) : ViewModel() {

    private val _state = mutableStateOf(
        VerificationScreenState(
            isError = false,
            idUser = savedStateHandle[idUserVerificationKeyArg] ?: 0,
            isForgotPassword = savedStateHandle[isForgotPasswordArg] ?: false
        )
    )
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

            is VerificationEvent.OnVerificationForgotPassword -> {
                if (!_state.value.code.isError) {
                    verificationForgotPassword()
                }
            }
        }
    }

    private fun verification() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result = repository.verificationUser(state.value.idUser, state.value.code.text)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
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

    private fun verificationForgotPassword() = viewModelScope.launch {
        _state.value = state.value.copy(progress = true)
        when (val result =
            repository.verificationForgotPassword(state.value.idUser, state.value.code.text)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }

            is NetworkResult.Success -> {
                _state.value = state.value.copy(
                    progress = false,
                    successVerification = true,
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

            404 -> {
                _state.value = state.value.copy(
                    progress = false,
                    isError = true,
                    errorMessage = R.string.error_verification_code
                )
            }

            else -> _state.value = state.value.copy(
                progress = false,
                isError = true,
                errorMessage = R.string.unknown_error,
            )
        }
    }

    fun updateVerificationStatus() = viewModelScope.launch {
        _state.value = state.value.copy(successVerification = false)
    }
}