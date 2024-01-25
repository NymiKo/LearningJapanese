package com.example.peil.ui.screens.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val settingsScreenRoute = "settings_screen"

fun NavGraphBuilder.settingsScreen(onChangeNicknameScreen: (nickname: String) -> Unit, onBack: () -> Unit) {
    composable(settingsScreenRoute) {
        val viewModel: SettingsViewModel = hiltViewModel()
        SettingsScreen(viewModel = viewModel, onChangeNicknameScreen = onChangeNicknameScreen::invoke, onBack = onBack)
    }
}

fun NavController.navigateToSettingsScreen() {
    navigate(settingsScreenRoute)
}