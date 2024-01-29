package com.example.peil.ui.screens.create_account

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
        )
    ) {backStackEntry ->
        val email = backStackEntry.arguments?.getString(emailKeyArg)

        val viewModel = hiltViewModel<CreateAccountViewModel>()
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