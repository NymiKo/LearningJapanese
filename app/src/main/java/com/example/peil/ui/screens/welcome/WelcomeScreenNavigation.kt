package com.example.peil.ui.screens.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val welcomeScreenRoute = "welcome_screen"

fun NavGraphBuilder.welcomeScreen(onLoginClick: () -> Unit, onRegistrationEmailClick: () -> Unit) {
    composable(welcomeScreenRoute) {
        WelcomeScreen(onLoginClick = onLoginClick::invoke, onRegistrationEmailClick = onRegistrationEmailClick::invoke)
    }
}

fun NavController.navigateToWelcomeScreen() {
    navigate(welcomeScreenRoute)
}

fun NavController.popBackStackToWelcomeScreen() {
    popBackStack(route = welcomeScreenRoute, inclusive = false)
}