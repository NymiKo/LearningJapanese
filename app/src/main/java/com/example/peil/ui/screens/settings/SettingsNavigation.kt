package com.example.peil.ui.screens.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val settingsScreenRoute = "settings_screen"

fun NavGraphBuilder.settingsScreen(onBack: () -> Unit) {
    composable(settingsScreenRoute) {
        SettingsScreen(onBack = onBack)
    }
}

fun NavController.navigateToSettingsScreen() {
    navigate(settingsScreenRoute)
}