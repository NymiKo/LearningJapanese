package com.example.peil.ui.screens.create_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.screens.create_account.state.CreateAccountEvent
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.filed.OutlinedLoginField
import com.example.peil.ui.view_components.text.AuthorizationErrorMessage
import com.example.peil.ui.view_components.text.TextLabel

@Composable
fun CreateAccountScreen(
    onVerificationScreen: (idUser: Int) -> Unit,
    showHaveAccountDialog: () -> Unit,
    onBack: () -> Unit,
    email: String,
    viewModel: CreateAccountViewModel
) {
    var flag by remember {
        mutableStateOf(false)
    }
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (state.successCreateAccount) {
            onVerificationScreen(viewModel.state.value.idUser)
            viewModel.updateStateVerificationCode()
        }
        if (state.isOpenHaveAccountDialog) {
            showHaveAccountDialog()
            viewModel.updateStateDialog()
        }

        BaseAppBar(
            title = R.string.create_account,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack
        ) { onBack() }
        if (state.isError) {
            AuthorizationErrorMessage(errorMessage = state.errorMessage)
        }
        TextLabel(
            modifier = Modifier.padding(top = if (!state.isError) 30.dp else 0.dp),
            textLabel = R.string.email
        )
        OutlinedLoginField(
            value = state.email.text,
            valueChange = { viewModel.createEvent(CreateAccountEvent.EnteringEmail(it)) },
            error = state.email.isError
        )
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.name)
        OutlinedLoginField(
            value = state.nickname.text,
            valueChange = { viewModel.createEvent(CreateAccountEvent.EnteringNickname(it)) },
            error = state.nickname.isError
        )
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.password_min_char)
        OutlinedLoginField(
            value = state.password.text,
            password = true,
            valueChange = { viewModel.createEvent(CreateAccountEvent.EnteringPassword(it)) },
            error = state.password.isError
        )
        LoginButton(
            modifier = Modifier.padding(top = 30.dp),
            textButton = R.string.registration,
            onClick = {
                viewModel.createEvent(CreateAccountEvent.OnCreateAccount)
            },
            enabled = !state.progress
        ) {
            if (state.progress) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
@Preview
private fun CreateAccountScreenPreview() {
    CreateAccountScreen(
        {}, {}, {}, "", hiltViewModel()
    )
}