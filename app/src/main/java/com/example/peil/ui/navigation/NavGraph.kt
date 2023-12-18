package com.example.peil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.peil.ui.screens.create_account.CreateAccountScreen
import com.example.peil.ui.screens.create_account.CreateAccountViewModel
import com.example.peil.ui.screens.login.LoginScreen
import com.example.peil.ui.screens.registration.RegistrationEmailScreen
import com.example.peil.ui.screens.registration.RegistrationEmailViewModel
import com.example.peil.ui.screens.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Welcome.route) {
        composable(route = Screens.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(route = Screens.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screens.RegistrationEmail.route) {
            val viewModel = hiltViewModel<RegistrationEmailViewModel>()
            RegistrationEmailScreen(navController, viewModel)
        }

        composable(route = Screens.CreateAccount.route + "/{email}") { backStackEntry ->
            val viewModel = hiltViewModel<CreateAccountViewModel>()
            CreateAccountScreen(navController, viewModel, backStackEntry.arguments?.getString("email"))
        }
    }
}