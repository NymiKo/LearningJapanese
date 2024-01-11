package com.example.peil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.peil.ui.screens.create_account.createAccountScreen
import com.example.peil.ui.screens.create_account.dialog_screen.haveAccountDialog
import com.example.peil.ui.screens.create_account.dialog_screen.navigateToHaveAccountDialog
import com.example.peil.ui.screens.create_account.navigateToCreateAccountScreen
import com.example.peil.ui.screens.learning_lesson.learningLessonScreen
import com.example.peil.ui.screens.learning_lesson.navigateToLearningLessonScreen
import com.example.peil.ui.screens.lesson_completion.lessonCompletion
import com.example.peil.ui.screens.lesson_completion.navigateToLessonCompletionScreen
import com.example.peil.ui.screens.lessons_list.lessonsListScreen
import com.example.peil.ui.screens.lessons_list.lessonsListScreenRoute
import com.example.peil.ui.screens.lessons_list.navigateToLessonsListScreen
import com.example.peil.ui.screens.lessons_list.navigateToLessonsListScreenWithClearBackStack
import com.example.peil.ui.screens.login.navigation.loginScreen
import com.example.peil.ui.screens.login.navigation.navigateToLoginScreen
import com.example.peil.ui.screens.registration.navigateToRegistrationEmailScreen
import com.example.peil.ui.screens.registration.registrationScreen
import com.example.peil.ui.screens.welcome.navigateToWelcomeScreen
import com.example.peil.ui.screens.welcome.popBackStackToWelcomeScreen
import com.example.peil.ui.screens.welcome.welcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = lessonsListScreenRoute
    ) {
        welcomeScreen(
            onLoginClick = navController::navigateToLoginScreen,
            onRegistrationEmailClick = navController::navigateToRegistrationEmailScreen
        )

        loginScreen(
            onLessonsListScreen = navController::navigateToLessonsListScreen,
            onBack = navController::popBackStackToWelcomeScreen
        )

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

        lessonsListScreen(
            onWelcomeScreen = navController::navigateToWelcomeScreen,
            onLearningLesson = navController::navigateToLearningLessonScreen
        )

        learningLessonScreen(
            onLessonCompletionScreen = navController::navigateToLessonCompletionScreen
        )

        lessonCompletion(onLessonsListScreen = navController::navigateToLessonsListScreenWithClearBackStack)
    }
}