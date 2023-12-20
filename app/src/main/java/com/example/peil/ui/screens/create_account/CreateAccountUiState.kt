package com.example.peil.ui.screens.create_account

sealed class CreateAccountUiState {
    object LOADING: CreateAccountUiState()
    data class SUCCESS(val token: String): CreateAccountUiState()
    data class ERROR(val errorMessage: Int): CreateAccountUiState()
}