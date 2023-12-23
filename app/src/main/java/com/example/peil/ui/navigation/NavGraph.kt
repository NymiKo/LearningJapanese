package com.example.peil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.peil.ui.screens.create_account.createAccountScreen
import com.example.peil.ui.screens.create_account.dialog_screen.haveAccountDialog
import com.example.peil.ui.screens.create_account.dialog_screen.navigateToHaveAccountDialog
import com.example.peil.ui.screens.create_account.navigateToCreateAccountScreen
import com.example.peil.ui.screens.login.loginScreen
import com.example.peil.ui.screens.login.navigateToLoginScreen
import com.example.peil.ui.screens.registration.navigateToRegistrationEmailScreen
import com.example.peil.ui.screens.registration.registrationScreen
import com.example.peil.ui.screens.welcome.popBackStackToWelcomeScreen
import com.example.peil.ui.screens.welcome.welcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ) {
        welcomeScreen(
            onLoginClick = navController::navigateToLoginScreen,
            onRegistrationEmailClick = navController::navigateToRegistrationEmailScreen
        )

        loginScreen({}, onBack = navController::popBackStackToWelcomeScreen)

        registrationScreen(
            onCreateAccountClick = { email -> navController.navigateToCreateAccountScreen(email) },
            onLoginClick = navController::navigateToLoginScreen,
            onBack = navController::popBackStack
        )

        createAccountScreen(
            {},
            showHaveAccountDialog = navController::navigateToHaveAccountDialog,
            onBack = navController::popBackStack
        )

        haveAccountDialog(
            onLoginClick = navController::navigateToLoginScreen,
            onDismissRequest = navController::popBackStack
        )
    }
}