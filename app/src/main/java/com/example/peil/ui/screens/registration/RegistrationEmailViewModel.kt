package com.example.peil.ui.screens.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationEmailViewModel @Inject constructor(): ViewModel() {

    var email by mutableStateOf("")
        private set

    var error by mutableStateOf(false)
        private set

    fun updateEmail(inputEmail: String) {
        email = inputEmail
    }

    fun checkFieldEmail() {
        error = email.isEmpty()
    }
}