package com.example.peil.ui.screens.create_account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.R
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val repository: CreateAccountRepository
): ViewModel() {

    private val _state = MutableLiveData<CreateAccountUiState>()
    val state: LiveData<CreateAccountUiState> = _state

    var email by mutableStateOf("")
        private set

    var nickname by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isOpenHaveAccountDialog by mutableStateOf(false)
        private set

    fun updateNickname(inputNickname: String) {
        nickname = inputNickname
    }

    fun updatePassword(inputPassword: String) {
        password = inputPassword
    }

    fun updateEmail(inputEmail: String) {
        email = inputEmail
    }

    fun updateOpenHaveAccountDialog(isOpen: Boolean) {
        isOpenHaveAccountDialog = isOpen
    }

    fun createAccount() = viewModelScope.launch {
        _state.value = CreateAccountUiState.LOADING
        when(val result = repository.createAccount(email, nickname, password)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }
            is NetworkResult.Success -> {
                _state.value = CreateAccountUiState.SUCCESS(result.data)
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when(errorCode) {
            105 -> _state.value = CreateAccountUiState.ERROR(R.string.check_your_internet_connection)
            400 -> {
                _state.value = CreateAccountUiState.HAVE_ACCOUNT
                isOpenHaveAccountDialog = true
            }
            else -> _state.value = CreateAccountUiState.ERROR(R.string.unknown_error)
        }
    }
}