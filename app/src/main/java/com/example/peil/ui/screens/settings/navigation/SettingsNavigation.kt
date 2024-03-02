package com.example.peil.ui.screens.settings.navigation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.profile.profileScreenRoute
import com.example.peil.ui.screens.settings.SettingsScreen
import com.example.peil.ui.screens.settings.SettingsViewModel

const val updateSettingsArg = "update_settings"
const val settingsScreenRoute = "settings_screen"

fun NavGraphBuilder.settingsScreen(
    onChangeNicknameScreen: (nickname: String) -> Unit,
    onWelcomeScreen: () -> Unit,
    onBack: (updateProfile: Boolean) -> Unit
) {
    composable(
        route = settingsScreenRoute,
        enterTransition = {
            when (initialState.destination.route) {
                profileScreenRoute -> {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    )
                }

                else -> null
            }
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        }
    ) {
        val viewModel: SettingsViewModel = hiltViewModel()
        val updateSettings = it.savedStateHandle.get<Boolean>(updateSettingsArg) ?: false
        SettingsScreen(
            viewModel = viewModel,
            onChangeNicknameScreen = onChangeNicknameScreen::invoke,
            onWelcomeScreen = onWelcomeScreen::invoke,
            onBack = { onBack(it) },
            updateSettings = updateSettings
        )
    }
}

fun NavController.navigateToSettingsScreen() {
    navigate(settingsScreenRoute)
}

fun NavController.popBackStackToSettingsScreen(route: String, updateProfile: Boolean) {
    previousBackStackEntry?.savedStateHandle?.set(updateSettingsArg, updateProfile)
    popBackStack(route = route, inclusive = false)
}