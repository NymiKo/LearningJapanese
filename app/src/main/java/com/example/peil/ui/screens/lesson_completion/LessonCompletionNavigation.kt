package com.example.peil.ui.screens.lesson_completion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val lessonCompletionRoute = "lesson_completion"

fun NavGraphBuilder.lessonCompletion(onLessonsListScreen: () -> Unit) {
    composable(lessonCompletionRoute) {
        LessonCompletionScreen(onLessonsListScreen::invoke)
    }
}

fun NavController.navigateToLessonCompletionScreen() {
    navigate(lessonCompletionRoute)
}