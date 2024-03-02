package com.example.peil.ui.screens.new_password.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peil.ui.screens.new_password.NewPasswordScreen
import com.example.peil.ui.screens.new_password.NewPasswordViewModel
import com.example.peil.ui.screens.new_password.state.NewPasswordEvent

const val idUserNewPasswordArg = "id_user_new_password"
const val newPasswordScreen = "new_password_screen"
const val newPasswordScreenRoute = "$newPasswordScreen/{$idUserNewPasswordArg}"

fun NavGraphBuilder.newPasswordScreen(onLoginScreen: () -> Unit) {
    composable(
        route = newPasswordScreenRoute,
        arguments = listOf(
            navArgument(idUserNewPasswordArg) { type = NavType.IntType }
        ),
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {
        val viewModel: NewPasswordViewModel = hiltViewModel()
        NewPasswordScreen(viewModel = viewModel, onLoginScreen = onLoginScreen::invoke)
    }
}

fun NavController.navigateToNewPasswordScreen(idUser: Int) {
    navigate("$newPasswordScreen/$idUser")
}