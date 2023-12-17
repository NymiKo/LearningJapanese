package com.example.peil.ui.navigation

sealed class Screens(val route: String) {
    object Welcome: Screens("welcome_screen")
    object Login: Screens("login_screen")
    object RegistrationEmail: Screens("registration_email_screen")
    object CreateAccount: Screens("create_account_screen")
}
