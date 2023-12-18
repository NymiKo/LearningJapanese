package com.example.peil.ui.screens.create_account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val repository: CreateAccountRepository
): ViewModel() {

    var nickname by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateNickname(inputNickname: String) {
        nickname = inputNickname
    }

    fun updatePassword(inputPassword: String) {
        password = inputPassword
    }
}