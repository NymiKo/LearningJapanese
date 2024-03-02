package com.example.peil.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.peil.ui.screens.change_nickname.changeNicknameScreen
import com.example.peil.ui.screens.change_nickname.navigateToChangeNickname
import com.example.peil.ui.screens.profile.popBackStackToProfileScreen
import com.example.peil.ui.screens.settings.navigation.settingsScreen
import com.example.peil.ui.screens.settings.navigation.settingsScreenRoute
import com.example.peil.ui.screens.welcome.navigateToWelcomeScreen

const val settingsNavGraphRoute = "settings_nav_graph"

fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = settingsNavGraphRoute,
        startDestination = settingsScreenRoute
    ) {
        settingsScreen(
            onChangeNicknameScreen = { nickname -> navController.navigateToChangeNickname(nickname) },
            onWelcomeScreen = {
                navController.navigate(loginNavGraph) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            },
            onBack = navController::popBackStackToProfileScreen
        )

        changeNicknameScreen(
            onBack = { navController.popBackStack(route = settingsScreenRoute, inclusive = false)}
        )

        loginNavGraph(navController = navController)
    }
}