package com.example.peil.ui.screens.new_password.state

sealed class NewPasswordEvent {
    data class EnteringNewPassword(val value: String): NewPasswordEvent()
    data class EnteringRepeatNewPassword(val value: String): NewPasswordEvent()
    object ChangePassword: NewPasswordEvent()
    data class GetIdUser(val idUser: Int): NewPasswordEvent()
}
