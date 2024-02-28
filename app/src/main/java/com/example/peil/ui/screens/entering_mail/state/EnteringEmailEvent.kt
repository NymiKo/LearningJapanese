package com.example.peil.ui.screens.entering_mail.state

sealed class EnteringEmailEvent {
    data class EnteringEmail(val email: String): EnteringEmailEvent()
    object SendingEmail: EnteringEmailEvent()
}
