package com.example.peil.ui.screens.verification.state

sealed class VerificationEvent {
    data class EnteringCode(val code: String): VerificationEvent()
    data class GetIdUser(val idUser: Int): VerificationEvent()
    object OnVerification: VerificationEvent()
    object OnVerificationForgotPassword: VerificationEvent()
}
