package com.example.peil.ui.screens.lessons_list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val lessonsListScreenRoute = "lessons_list"

fun NavGraphBuilder.lessonsListScreen(
    onLearningLesson: (idLesson: Int) -> Unit
) {
    composable(lessonsListScreenRoute) {
        val viewModel: LessonsListViewModel = hiltViewModel()
        LessonsListScreen(
            onLearningLesson = onLearningLesson::invoke,
            viewModel = viewModel
        )
    }
}

fun NavController.navigateToLessonsListScreen() {
    navigate(lessonsListScreenRoute) {
        popUpTo(lessonsListScreenRoute) {
            inclusive = true
        }
        launchSingleTop = true
    }
}