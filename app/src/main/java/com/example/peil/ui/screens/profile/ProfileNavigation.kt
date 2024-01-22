package com.example.peil.ui.screens.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val profileScreenRoute = "profile_screen"

fun NavGraphBuilder.profileScreen() {
    composable(profileScreenRoute) {
        val viewModel: ProfileViewModel = hiltViewModel()
        ProfileScreen(viewModel = viewModel)
    }
}

fun NavController.navigateToProfileScreen() {
    navigate(profileScreenRoute) {
        popUpTo(profileScreenRoute)
        launchSingleTop = true
    }
}