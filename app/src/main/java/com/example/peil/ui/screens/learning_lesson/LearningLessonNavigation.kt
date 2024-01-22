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

fun NavGraphBuilder.learningLessonScreen(onLessonCompletionScreen: (idLesson: Int) -> Unit, showCancelLessonDialog: () -> Unit) {
    composable(
        route = learningLessonScreenRoute,
        arguments = listOf(
            navArgument(idLessonKeyArg) { type = NavType.IntType}
        )
    ) { backStackEntry ->
        val idLesson = backStackEntry.arguments?.getInt(idLessonKeyArg) ?: 0

        val viewModel: LearningLessonViewModel = hiltViewModel()
        viewModel.getSubLessonsList(idLesson = idLesson)
        LearningLessonScreen(viewModel, idLesson, onLessonCompletionScreen::invoke, showCancelLessonDialog::invoke)
    }
}

fun NavController.navigateToLearningLessonScreen(idLesson: Int) {
    navigate("$learningLessonScreen/$idLesson")
}