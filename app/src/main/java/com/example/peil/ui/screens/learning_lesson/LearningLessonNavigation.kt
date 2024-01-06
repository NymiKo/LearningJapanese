package com.example.peil.ui.screens.learning_lesson

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val learningLessonScreenRoute = "learning_lesson"

fun NavGraphBuilder.learningLessonScreen() {
    composable(learningLessonScreenRoute) {
        val viewModel: LearningLessonViewModel = hiltViewModel()
        LearningLessonScreen(viewModel)
    }
}

fun NavController.navigateToLearningLessonScreen() {
    navigate(learningLessonScreenRoute)
}