package com.example.peil.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.peil.ui.screens.lessons_list.lessonsListScreenRoute
import com.example.peil.ui.screens.profile.profileScreenRoute

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Study : BottomBarScreen(
        route = lessonsListScreenRoute,
        title = "Учиться",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = profileScreenRoute,
        title = "Профиль",
        icon = Icons.Default.Person
    )
}
