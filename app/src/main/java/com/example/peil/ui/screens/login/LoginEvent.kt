package com.example.peil.ui.screens.login

sealed class LoginEvent {
    data class EnteringEmail(val value: String): LoginEvent()
    data class EnteringPassword(val value: String): LoginEvent()
    object OnLogin: LoginEvent()
}
