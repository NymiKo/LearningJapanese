package com.example.peil.ui.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val loginScreenRoute = "login_screen"

fun NavGraphBuilder.loginScreen(onLoginClick: () -> Unit, onBack: () -> Unit) {
    composable(loginScreenRoute) {
        LoginScreen(onLoginClick::invoke, onBack::invoke)
    }
}

fun NavController.navigateToLoginScreen() {
    navigate(loginScreenRoute)
}