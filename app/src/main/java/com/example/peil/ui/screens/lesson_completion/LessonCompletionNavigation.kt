package com.example.peil.ui.screens.lesson_completion

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val idLessonCompletedKeyArg = "idLessonCompleted"
private const val lessonCompletionScreen = "lesson_completion"
private const val lessonCompletionRoute = "lesson_completion/{$idLessonCompletedKeyArg}"

fun NavGraphBuilder.lessonCompletion(onLessonsListScreen: () -> Unit) {
    composable(
        route = lessonCompletionRoute,
        arguments = listOf(
            navArgument(idLessonCompletedKeyArg) { type = NavType.IntType}
        )
    ) {
        val viewModel: LessonCompletionViewModel = hiltViewModel()
        LessonCompletionScreen(viewModel = viewModel, onLessonsListScreen::invoke)
    }
}

fun NavController.navigateToLessonCompletionScreen(idLesson: Int) {
    navigate("$lessonCompletionScreen/$idLesson")
}