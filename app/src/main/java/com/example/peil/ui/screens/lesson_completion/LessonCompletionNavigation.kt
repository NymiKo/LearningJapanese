package com.example.peil.ui.screens.lesson_completion

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val idLessonKeyArg = "idLessonCompleted"
private const val lessonCompletionScreen = "lesson_completion"
private const val lessonCompletionRoute = "lesson_completion/{$idLessonKeyArg}"

fun NavGraphBuilder.lessonCompletion(onLessonsListScreen: () -> Unit) {
    composable(
        route = lessonCompletionRoute,
        arguments = listOf(
            navArgument(idLessonKeyArg) { type = NavType.IntType}
        )
    ) { backStackEntry ->
        val idLesson = backStackEntry.arguments?.getInt(idLessonKeyArg) ?: 0
        val viewModel: LessonCompletionViewModel = hiltViewModel()
        LessonCompletionScreen(viewModel = viewModel, idLesson, onLessonsListScreen::invoke)
    }
}

fun NavController.navigateToLessonCompletionScreen(idLesson: Int) {
    navigate("$lessonCompletionScreen/$idLesson")
}