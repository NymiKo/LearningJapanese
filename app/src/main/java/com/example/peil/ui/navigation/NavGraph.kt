package com.example.peil.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.peil.ui.screens.bottom_nav_bar.NavigationBarWithContent
import com.example.peil.ui.screens.create_account.createAccountScreen
import com.example.peil.ui.screens.create_account.dialog_screen.haveAccountDialog
import com.example.peil.ui.screens.create_account.dialog_screen.navigateToHaveAccountDialog
import com.example.peil.ui.screens.create_account.navigateToCreateAccountScreen
import com.example.peil.ui.screens.entering_mail.navigation.enteringEmail
import com.example.peil.ui.screens.entering_mail.navigation.navigateToEnteringEmail
import com.example.peil.ui.screens.login.navigation.loginScreen
import com.example.peil.ui.screens.login.navigation.navigateToLoginScreen
import com.example.peil.ui.screens.login.navigation.navigateToLoginScreenWithClearStack
import com.example.peil.ui.screens.new_password.navigation.navigateToNewPasswordScreen
import com.example.peil.ui.screens.new_password.navigation.newPasswordScreen
import com.example.peil.ui.screens.registration.navigateToRegistrationEmailScreen
import com.example.peil.ui.screens.registration.registrationScreen
import com.example.peil.ui.screens.verification.navigation.navigateToVerificationScreen
import com.example.peil.ui.screens.verification.navigation.verificationScreen
import com.example.peil.ui.screens.welcome.popBackStackToWelcomeScreen
import com.example.peil.ui.screens.welcome.welcomeScreen
import com.example.peil.ui.screens.welcome.welcomeScreenRoute

const val loginNavGraph = "login_nav_graph"

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = welcomeScreenRoute,
        route = loginNavGraph
    ) {
        welcomeScreen(
            onLoginClick = navController::navigateToLoginScreen,
            onRegistrationEmailClick = navController::navigateToRegistrationEmailScreen
        )

        loginScreen(
            onLessonsListScreen = { navController.navigate(bottomNavGraphRoute) },
            onForgotPassword = navController::navigateToEnteringEmail,
            onBack = navController::popBackStackToWelcomeScreen
        )

        enteringEmail(
            onVerificationScreen = { idUser ->
                navController.navigateToVerificationScreen(
                    idUser = idUser,
                    isForgotPassword = true
                )
            }
        )

        newPasswordScreen(
            onLoginScreen = navController::navigateToLoginScreenWithClearStack
        )

        registrationScreen(
            onCreateAccountClick = { email -> navController.navigateToCreateAccountScreen(email) },
            onLoginClick = navController::navigateToLoginScreen,
            onBack = navController::popBackStack
        )

        createAccountScreen(
            onVerificationScreen = { idUser -> navController.navigateToVerificationScreen(idUser) },
            showHaveAccountDialog = navController::navigateToHaveAccountDialog,
            onBack = navController::popBackStack
        )

        haveAccountDialog(
            onLoginClick = navController::navigateToLoginScreen,
            onDismissRequest = navController::popBackStack
        )

        verificationScreen(
            onLessonsListScreen = { navController.navigate(bottomNavGraphRoute) },
            onNewPasswordScreen = { idUser -> navController.navigateToNewPasswordScreen(idUser) }
        )
    }
}