package com.example.peil.ui.screens.create_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.peil.R
import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import com.example.peil.ui.screens.create_account.data.CreateAccountRepositoryImpl
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.OutlinedLoginField
import com.example.peil.ui.view_components.TextLabel

@Composable
fun CreateAccountScreen(
    navController: NavController,
    viewModel: CreateAccountViewModel,
    email: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BaseAppBar(
            title = R.string.create_account,
            imageVector = Icons.Default.ArrowBack
        ) { navController.popBackStack() }
        TextLabel(modifier = Modifier.padding(top = 30.dp), textLabel = R.string.email)
        OutlinedLoginField(value = email) { }
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.name)
        OutlinedLoginField(value = viewModel.nickname) { viewModel.updateNickname(it) }
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.password_min_char)
        OutlinedLoginField(value = viewModel.password, password = true) {
            viewModel.updatePassword(
                it
            )
        }
        LoginButton(textButton = R.string.registration) { }
    }
}

@Composable
@Preview
private fun CreateAccountScreenPreview() {
    CreateAccountScreen(
        rememberNavController(),
        CreateAccountViewModel(CreateAccountRepositoryImpl()),
        ""
    )
}