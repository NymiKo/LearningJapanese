package com.example.peil.ui.screens.learning_lesson.dialog

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val cancelLessonDialogRoute = "cancel_lesson"

fun NavGraphBuilder.cancelLessonDialog(onLessonsListScreen: () -> Unit, onDismissRequest: () -> Unit) {
    dialog(cancelLessonDialogRoute) {
        CancelLessonDialog(onLessonsListScreen = onLessonsListScreen, onDismissRequest = onDismissRequest)
    }
}

fun NavController.navigateToCancelLessonDialog() {
    navigate(cancelLessonDialogRoute)
}