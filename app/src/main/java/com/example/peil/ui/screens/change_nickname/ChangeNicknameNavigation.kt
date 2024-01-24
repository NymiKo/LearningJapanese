package com.example.peil.ui.screens.change_nickname

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val changeNicknameScreenRoute = "change_nickname_screen"

fun NavGraphBuilder.changeNicknameScreen(onBack: () -> Unit) {
    composable(changeNicknameScreenRoute) {
        val viewModel: ChangeNicknameViewModel = hiltViewModel()
        ChangeNicknameScreen(viewModel = viewModel, onBack = onBack::invoke)
    }
}

fun NavController.navigateToChangeNickname() {
    navigate(changeNicknameScreenRoute)
}