package com.example.peil.ui.screens.lessons_list

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.profile.profileScreenRoute

const val lessonsListScreenRoute = "lessons_list"

fun NavGraphBuilder.lessonsListScreen(
    onLearningLesson: (idLesson: Int) -> Unit
) {
    composable(
        route = lessonsListScreenRoute,
        enterTransition = {
            when(initialState.destination.route) {
                profileScreenRoute -> {
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(300)
                    )
                }

                else -> null
            }
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        }
    ) {
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