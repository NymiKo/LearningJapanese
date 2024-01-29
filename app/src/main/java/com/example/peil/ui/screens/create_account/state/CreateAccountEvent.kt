package com.example.peil.ui.screens.create_account.state

sealed class CreateAccountEvent {
    data class EnteringEmail(val value: String): CreateAccountEvent()
    data class EnteringNickname(val value: String): CreateAccountEvent()
    data class EnteringPassword(val value: String): CreateAccountEvent()
    object OnCreateAccount: CreateAccountEvent()
}
