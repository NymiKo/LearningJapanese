package com.example.peil.ui.screens.verification.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peil.ui.screens.verification.VerificationScreen
import com.example.peil.ui.screens.verification.VerificationViewModel
import com.example.peil.ui.screens.verification.state.VerificationEvent

const val idUserKeyArg = "id_user"
const val isForgotPasswordArg = "is_forgot_password"
const val verificationScreen = "verification_screen"
const val verificationScreenRoute = "$verificationScreen/{$idUserKeyArg}/{$isForgotPasswordArg}"

fun NavGraphBuilder.verificationScreen(onLessonsListScreen: () -> Unit, onNewPasswordScreen: (idUser: Int) -> Unit) {
    composable(
        route = verificationScreenRoute,
        arguments = listOf(
            navArgument(idUserKeyArg) { type = NavType.IntType },
            navArgument(isForgotPasswordArg) { type = NavType.BoolType }
        ),
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {backStackEntry ->
        val idUser = backStackEntry.arguments?.getInt(idUserKeyArg)
        val isForgotPassword = backStackEntry.arguments?.getBoolean(isForgotPasswordArg) ?: false

        val viewModel: VerificationViewModel = hiltViewModel()
        viewModel.createEvent(VerificationEvent.GetIdUser(idUser ?: 0))
        VerificationScreen(
            viewModel = viewModel,
            isForgotPassword = isForgotPassword,
            onLessonsListScreen = onLessonsListScreen::invoke,
            onNewPasswordScreen = { onNewPasswordScreen(it) }
        )
    }
}

fun NavController.navigateToVerificationScreen(idUser: Int, isForgotPassword: Boolean = false) {
    navigate("$verificationScreen/$idUser/$isForgotPassword")
}