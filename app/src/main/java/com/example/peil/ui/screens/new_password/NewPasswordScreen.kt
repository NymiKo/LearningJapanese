package com.example.peil.ui.screens.new_password

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.screens.new_password.state.NewPasswordEvent
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.filed.OutlinedLoginField
import com.example.peil.ui.view_components.text.AuthorizationErrorMessage
import com.example.peil.ui.view_components.text.TextLabel
import kotlinx.coroutines.launch

@Composable
fun NewPasswordScreen(
    viewModel: NewPasswordViewModel,
    onLoginScreen: () -> Unit
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (state.successChangePassword) {
            Toast.makeText(LocalContext.current, R.string.password_has_been_changed, Toast.LENGTH_LONG).show()
            onLoginScreen()
            viewModel.updateStatusNewPassword()
        }

        if (state.isError) {
            AuthorizationErrorMessage(
                modifier = Modifier.padding(top = 16.dp),
                errorMessage = state.errorMessage
            )
        }

        SnackbarHost(snackbarHostState.value)
        TextLabel(
            modifier = Modifier.padding(top = 30.dp),
            textLabel = R.string.enter_new_password
        )
        OutlinedLoginField(
            value = state.newPassword.text,
            error = state.newPassword.isError,
            password = true,
            valueChange = { viewModel.createEvent(NewPasswordEvent.EnteringNewPassword(it)) }
        )
        TextLabel(
            modifier = Modifier.padding(top = 16.dp),
            textLabel = R.string.repeat_new_password
        )
        OutlinedLoginField(
            value = state.repeatNewPassword.text,
            error = state.repeatNewPassword.isError,
            password = true,
            valueChange = { viewModel.createEvent(NewPasswordEvent.EnteringRepeatNewPassword(it)) }
        )
        Spacer(modifier = Modifier.weight(1F))
        LoginButton(
            modifier = Modifier.padding(bottom = 16.dp),
            textButton = R.string.continue_text,
            onClick = {
                viewModel.createEvent(NewPasswordEvent.ChangePassword)
            },
            enabled = !state.progress,
            content = {
                if (state.progress) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                }
            }
        )
    }
}

@Preview
@Composable
private fun NewPasswordScreenPreview() {
    NewPasswordScreen(hiltViewModel(), {})
}