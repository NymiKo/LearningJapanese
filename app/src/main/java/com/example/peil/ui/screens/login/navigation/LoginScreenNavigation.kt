package com.example.peil.ui.screens.login.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.ui.graphics.TransformOrigin
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.login.LoginScreen
import com.example.peil.ui.screens.login.LoginViewModel

const val loginScreenRoute = "login_screen"

fun NavGraphBuilder.loginScreen(onLessonsListScreen: () -> Unit, onForgotPassword: () -> Unit, onBack: () -> Unit) {
    composable(
        route = loginScreenRoute,
        enterTransition = {
            scaleIn(animationSpec = tween(300))
        },
        popExitTransition = {
            scaleOut(animationSpec = tween(300))
        }
    ) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(onLessonsListScreen::invoke, onForgotPassword::invoke, onBack::invoke, viewModel)
    }
}

fun NavController.navigateToLoginScreen() {
    navigate(loginScreenRoute)
}

fun NavController.navigateToLoginScreenWithClearStack() {
    navigate(loginScreenRoute) {
        popUpTo(loginScreenRoute) {
            inclusive = true
        }
    }
}