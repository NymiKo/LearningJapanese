package com.example.peil.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.peil.ui.screens.settings.settingsScreen
import com.example.peil.ui.screens.settings.settingsScreenRoute

const val settingsNavGraphRoute = "settings_nav_graph"

fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = settingsNavGraphRoute,
        startDestination = settingsScreenRoute
    ) {
        settingsScreen(
            onBack = navController::popBackStack
        )
    }
}