package com.example.peil.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.peil.ui.screens.learning_lesson.dialog.cancelLessonDialog
import com.example.peil.ui.screens.learning_lesson.dialog.navigateToCancelLessonDialog
import com.example.peil.ui.screens.learning_lesson.learningLessonScreen
import com.example.peil.ui.screens.lesson_completion.lessonCompletion
import com.example.peil.ui.screens.lesson_completion.navigateToLessonCompletionScreen
import com.example.peil.ui.screens.lessons_list.navigateToLessonsListScreen

const val learningLessonGraphRoute = "learning_lesson_graph"

fun NavGraphBuilder.learningLessonNavGraph(
    navController: NavHostController
) {
    navigation(
        route = learningLessonGraphRoute,
        startDestination = learningLessonScreen
    ) {
        learningLessonScreen(
            onLessonCompletionScreen = { idLessonCompleted ->
                navController.navigateToLessonCompletionScreen(
                    idLessonCompleted
                )
            },
            showCancelLessonDialog = navController::navigateToCancelLessonDialog,
            onBack = navController::popBackStack
        )

        lessonCompletion(onLessonsListScreen = navController::navigateToLessonsListScreen)

        cancelLessonDialog(
            onLessonsListScreen = navController::navigateToLessonsListScreen,
            onDismissRequest = navController::popBackStack
        )
    }
}