package com.example.peil.ui.screens.registration

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val registrationScreenRoute = "registration_screen"

fun NavGraphBuilder.registrationScreen(
    onCreateAccountClick: (email: String) -> Unit,
    onLoginClick: () -> Unit,
    onBack: () -> Unit
) {
    composable(registrationScreenRoute) {
        val viewModel = hiltViewModel<RegistrationEmailViewModel>()
        RegistrationEmailScreen(
            onCreateAccountClick = onCreateAccountClick::invoke,
            onLoginClick = onLoginClick::invoke,
            onBack = onBack::invoke,
            viewModel = viewModel
        )
    }
}

fun NavController.navigateToRegistrationEmailScreen() {
    navigate(registrationScreenRoute)
}