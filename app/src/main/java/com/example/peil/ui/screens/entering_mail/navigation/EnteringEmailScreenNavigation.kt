package com.example.peil.ui.screens.entering_mail.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.entering_mail.EnteringEmailScreen
import com.example.peil.ui.screens.entering_mail.EnteringEmailViewModel

const val enteringEmailScreenRoute = "entering_email"

fun NavGraphBuilder.enteringEmail(onVerificationScreen: (idUser: Int) -> Unit) {
    composable(
        route = enteringEmailScreenRoute,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {
        val viewModel: EnteringEmailViewModel = hiltViewModel()
        EnteringEmailScreen(viewModel = viewModel, onVerificationScreen = onVerificationScreen::invoke)
    }
}

fun NavController.navigateToEnteringEmail() {
    navigate(enteringEmailScreenRoute)
}