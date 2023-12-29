package com.example.peil.ui.screens.lessons_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.peil.NavigationBarWithContent

const val lessonsListScreenRoute = "lessons_list"

fun NavGraphBuilder.lessonsListScreen(onWelcomeScreen: () -> Unit) {
    composable(lessonsListScreenRoute) {
        NavigationBarWithContent(onWelcomeScreen = onWelcomeScreen::invoke)
    }
}

fun NavController.navigateToLessonsListScreen() {
    navigate(lessonsListScreenRoute)
}