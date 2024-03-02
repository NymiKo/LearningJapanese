package com.example.peil.ui.screens.verification.state

sealed class VerificationEvent {
    data class EnteringCode(val code: String): VerificationEvent()
    object OnVerification: VerificationEvent()
    object OnVerificationForgotPassword: VerificationEvent()
}
