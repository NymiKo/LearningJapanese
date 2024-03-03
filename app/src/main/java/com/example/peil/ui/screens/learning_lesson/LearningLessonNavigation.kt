package com.example.peil.ui.screens.learning_lesson

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val idLessonKeyArg = "idLesson"
const val learningLessonScreen = "learning_lesson"
private const val learningLessonScreenRoute = "learning_lesson/{$idLessonKeyArg}"

fun NavGraphBuilder.learningLessonScreen(
    onLessonCompletionScreen: (idLesson: Int) -> Unit,
    showCancelLessonDialog: () -> Unit,
    onBack: () -> Unit
) {
    composable(
        route = learningLessonScreenRoute,
        arguments = listOf(
            navArgument(idLessonKeyArg) { type = NavType.IntType }
        )
    ) {
        val viewModel: LearningLessonViewModel = hiltViewModel()
        LearningLessonScreen(
            viewModel,
            onLessonCompletionScreen::invoke,
            showCancelLessonDialog::invoke,
            onBack = onBack::invoke
        )
    }
}

fun NavController.navigateToLearningLessonScreen(idLesson: Int) {
    navigate("$learningLessonScreen/$idLesson")
}