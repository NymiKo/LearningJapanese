package com.example.peil.ui.screens.create_account

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peil.ui.screens.create_account.state.CreateAccountEvent

private const val emailKeyArg = "email"
private const val createAcountScreen = "create_account"
private const val createAcountScreenRoute = "create_account/{$emailKeyArg}"

fun NavGraphBuilder.createAccountScreen(onLessonsListScreen: () -> Unit, showHaveAccountDialog: () -> Unit, onBack: () -> Unit) {
    composable(
        route = createAcountScreenRoute,
        arguments = listOf(
            navArgument(emailKeyArg) { type = NavType.StringType }
        ),
        enterTransition = {
            slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
        }
    ) {backStackEntry ->
        val email = backStackEntry.arguments?.getString(emailKeyArg)

        val viewModel = hiltViewModel<CreateAccountViewModel>()
        viewModel.createEvent(CreateAccountEvent.EnteringEmail(email ?: ""))
        CreateAccountScreen(
            onLessonsListScreen = onLessonsListScreen::invoke,
            showHaveAccountDialog = showHaveAccountDialog,
            onBack = onBack::invoke,
            viewModel = viewModel,
            email = email ?: ""
        )
    }
}

fun NavController.navigateToCreateAccountScreen(email: String) {
    if (email.isNotEmpty()) {
        navigate("$createAcountScreen/$email")
    }
}