package com.example.peil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.peil.ui.screens.learning_lesson.navigateToLearningLessonScreen
import com.example.peil.ui.screens.lessons_list.lessonsListScreen
import com.example.peil.ui.screens.lessons_list.lessonsListScreenRoute
import com.example.peil.ui.screens.profile.profileScreen
import com.example.peil.ui.screens.settings.navigation.navigateToSettingsScreen

const val bottomNavGraphRoute = "bottom_nav_graph"

@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier, navController = navController,
        route = bottomNavGraphRoute,
        startDestination = lessonsListScreenRoute
    ) {
        lessonsListScreen(
            onLearningLesson = { idLesson -> navController.navigateToLearningLessonScreen(idLesson) }
        )
        profileScreen(
            onSettingsScreen = navController::navigateToSettingsScreen
        )

        learningLessonNavGraph(navController = navController)

        settingsNavGraph(navController = navController)
    }
}