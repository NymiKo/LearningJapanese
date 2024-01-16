package com.example.peil.ui.screens.lessons_list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.NavigationBarWithContent

const val lessonsListScreenRoute = "lessons_list"

fun NavGraphBuilder.lessonsListScreen(
    onWelcomeScreen: () -> Unit,
    onLearningLesson: (idLesson: Int) -> Unit
) {
    composable(lessonsListScreenRoute) {
        val viewModel: LessonsListViewModel = hiltViewModel()
        NavigationBarWithContent(
            onWelcomeScreen = onWelcomeScreen::invoke,
            onLearningLesson = onLearningLesson::invoke,
            lessonsListViewModel = viewModel
        )
    }
}

fun NavController.navigateToLessonsListScreen() {
    navigate(lessonsListScreenRoute)
}

fun NavController.navigateToLessonsListScreenWithClearBackStack() {
    navigate(lessonsListScreenRoute) {
        popUpTo(lessonsListScreenRoute) {
            inclusive = true
        }
    }
}