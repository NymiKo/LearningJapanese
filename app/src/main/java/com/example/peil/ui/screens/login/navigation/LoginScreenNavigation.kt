package com.example.peil.ui.screens.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.login.LoginScreen
import com.example.peil.ui.screens.login.LoginViewModel

const val loginScreenRoute = "login_screen"

fun NavGraphBuilder.loginScreen(onLessonsListScreen: () -> Unit, onBack: () -> Unit) {
    composable(loginScreenRoute) {
        val viewModel: LoginViewModel = hiltViewModel()
        LoginScreen(onLessonsListScreen::invoke, onBack::invoke, viewModel)
    }
}

fun NavController.navigateToLoginScreen() {
    navigate(loginScreenRoute)
}