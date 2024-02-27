package com.example.peil.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.bottom_nav_bar.NavigationBarWithContent
import com.example.peil.ui.screens.create_account.createAccountScreen
import com.example.peil.ui.screens.create_account.dialog_screen.haveAccountDialog
import com.example.peil.ui.screens.create_account.dialog_screen.navigateToHaveAccountDialog
import com.example.peil.ui.screens.create_account.navigateToCreateAccountScreen
import com.example.peil.ui.screens.learning_lesson.dialog.cancelLessonDialog
import com.example.peil.ui.screens.learning_lesson.dialog.navigateToCancelLessonDialog
import com.example.peil.ui.screens.learning_lesson.learningLessonScreen
import com.example.peil.ui.screens.lesson_completion.lessonCompletion
import com.example.peil.ui.screens.lesson_completion.navigateToLessonCompletionScreen
import com.example.peil.ui.screens.lessons_list.navigateToLessonsListScreen
import com.example.peil.ui.screens.login.navigation.loginScreen
import com.example.peil.ui.screens.login.navigation.navigateToLoginScreen
import com.example.peil.ui.screens.registration.navigateToRegistrationEmailScreen
import com.example.peil.ui.screens.registration.registrationScreen
import com.example.peil.ui.screens.verification.navigation.navigateToVerificationScreen
import com.example.peil.ui.screens.verification.navigation.verificationScreen
import com.example.peil.ui.screens.welcome.navigateToWelcomeScreen
import com.example.peil.ui.screens.welcome.popBackStackToWelcomeScreen
import com.example.peil.ui.screens.welcome.welcomeScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = bottomNavGraphRoute
    ) {
        welcomeScreen(
            onLoginClick = navController::navigateToLoginScreen,
            onRegistrationEmailClick = navController::navigateToRegistrationEmailScreen
        )

        loginScreen(
            onLessonsListScreen = { navController.navigate(bottomNavGraphRoute) },
            onBack = navController::popBackStackToWelcomeScreen
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
            onLessonsListScreen = { navController.navigate(bottomNavGraphRoute) }
        )

        composable(
            route = bottomNavGraphRoute
        ) {
            NavigationBarWithContent(onWelcomeScreen = navController::navigateToWelcomeScreen)
        }
    }
}