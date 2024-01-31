package com.example.peil.ui.screens.profile

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.lessons_list.lessonsListScreenRoute

const val profileScreenRoute = "profile_screen"

fun NavGraphBuilder.profileScreen(onSettingsScreen: () -> Unit) {
    composable(
        route = profileScreenRoute,
        enterTransition = {
            when(initialState.destination.route) {
                lessonsListScreenRoute -> {
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
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(viewModel = viewModel, onSettingsScreen = onSettingsScreen::invoke)
    }
}

fun NavController.navigateToProfileScreen() {
    navigate(profileScreenRoute) {
        popUpTo(profileScreenRoute)
        launchSingleTop = true
    }
}