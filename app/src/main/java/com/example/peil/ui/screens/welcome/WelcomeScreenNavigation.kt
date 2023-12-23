package com.example.peil.ui.screens.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val welcomeScreenRoute = "welcome_screen"

fun NavGraphBuilder.welcomeScreen(onLoginClick: () -> Unit) {
    composable(welcomeScreenRoute) {
        WelcomeScreen(onLoginClick::invoke)
    }
}

fun NavController.popBackStackToWelcomeScreen() {
    popBackStack(route = welcomeScreenRoute, inclusive = false)
}

fun NavController.navigateToWelcomeScreen() {
    navigate(welcomeScreenRoute)
}