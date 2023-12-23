package com.example.peil.ui.screens.create_account.dialog_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog

private const val haveAccountDialogRoute = "have_account"

fun NavGraphBuilder.haveAccountDialog(onLoginClick: () -> Unit, onDismissRequest: () -> Unit) {
    dialog(haveAccountDialogRoute) {
        HaveAccountDialog(onLoginClick = onLoginClick::invoke, onDismissRequest = onDismissRequest::invoke)
    }
}

fun NavController.navigateToHaveAccountDialog() {
    navigate(haveAccountDialogRoute)
}