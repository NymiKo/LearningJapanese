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

fun NavGraphBuilder.changeNicknameScreen(onBack: () -> Unit) {
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
    ) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString(nicknameKeyArg) ?: ""
        val viewModel: ChangeNicknameViewModel = hiltViewModel()
        viewModel.getNickname(nickname)
        ChangeNicknameScreen(viewModel = viewModel, onBack = onBack::invoke)
    }
}

fun NavController.navigateToChangeNickname(nickname: String) {
    if (nickname.isNotEmpty()) {
        navigate("$changeNicknameScreenRoute/$nickname")
    }
}