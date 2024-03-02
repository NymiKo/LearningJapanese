package com.example.peil.ui.screens.change_nickname

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val nicknameKeyArg = "nickname_arg"
const val changeNicknameScreenRoute = "change_nickname_screen"
const val changeNicknameScreenRouteWithArguments = "change_nickname_screen/{$nicknameKeyArg}"

fun NavGraphBuilder.changeNicknameScreen(onBack: (updateProfile: Boolean) -> Unit) {
    composable(
        changeNicknameScreenRouteWithArguments,
        arguments = listOf(navArgument(nicknameKeyArg) { type = NavType.StringType }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
    ) {
        val viewModel: ChangeNicknameViewModel = hiltViewModel()
        ChangeNicknameScreen(viewModel = viewModel, onBack = onBack::invoke)
    }
}

fun NavController.navigateToChangeNickname(nickname: String) {
    if (nickname.isNotEmpty()) {
        navigate("$changeNicknameScreenRoute/$nickname")
    }
}