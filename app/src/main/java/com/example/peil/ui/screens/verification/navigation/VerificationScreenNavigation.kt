package com.example.peil.ui.screens.verification.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peil.ui.screens.verification.VerificationScreen

const val idUserKeyArg = "id_user"
const val verificationScreen = "verification_screen"
const val verificationScreenRoute = "$verificationScreen/{$idUserKeyArg}"

fun NavGraphBuilder.verificationScreen(onLessonsListScreen: () -> Unit,) {
    composable(
        route = verificationScreenRoute,
        arguments = listOf(
            navArgument(idUserKeyArg) { type = NavType.IntType }
        ),
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {
        VerificationScreen(onLessonsListScreen = onLessonsListScreen::invoke)
    }
}

fun NavController.navigateToVerificationScreen(idUser: Int) {
    if (idUser != 0) {
        navigate("$verificationScreen/$idUser")
    }
}