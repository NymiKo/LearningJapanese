package com.example.peil.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.OutlinedLoginField
import com.example.peil.ui.view_components.TextLabel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onBack: () -> Unit,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val state = viewModel.state.value
        BaseAppBar(
            title = R.string.sign_in,
            imageVector = Icons.Default.ArrowBack,
            navigationClick = onBack::invoke
        )
        LoadingScreenContent(
            valueEmailChange = { viewModel.createEvent(LoginEvent.EnteringEmail(it)) },
            valuePasswordChange = { viewModel.createEvent(LoginEvent.EnteringPassword(it)) },
            valueEmail = state.email.text,
            valuePassword = state.password.text,
            progress = state.progress,
            onLogin = { viewModel.createEvent(LoginEvent.OnLogin) }
        )
    }
}

@Composable
private fun LoadingScreenContent(
    valueEmailChange: (email: String) -> Unit,
    valuePasswordChange: (password: String) -> Unit,
    valueEmail: String,
    valuePassword: String,
    progress: Boolean,
    onLogin: () -> Unit
) {
    Column {
        Header()
        FieldItem(
            modifier = Modifier.padding(top = 40.dp),
            textLabel = R.string.email,
            valueChange = valueEmailChange,
            value = valueEmail
        )
        FieldItem(
            modifier = Modifier.padding(top = 40.dp),
            textLabel = R.string.password,
            password = true,
            valueChange = valuePasswordChange,
            value = valuePassword
        )
        ForgotPasswordText()
        LoginButton(
            modifier = Modifier.padding(top = 30.dp),
            textButton = R.string.sign_in,
            onClick = onLogin::invoke,
            enabled = !progress
        ) {
            if (progress) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Blue)
            }
        }
    }
}

@Composable
private fun Header() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        text = stringResource(id = R.string.sign_in),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun FieldItem(
    modifier: Modifier = Modifier,
    textLabel: Int,
    password: Boolean = false,
    valueChange: (value: String) -> Unit,
    value: String
) {
    TextLabel(modifier, textLabel = textLabel)
    OutlinedLoginField(password = password, value = value) { newValue -> valueChange(newValue) }
}

@Composable
fun ForgotPasswordText() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = Blue,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(stringResource(id = R.string.forgot_password))
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        text = annotatedString,
        onClick = { }
    )
}

@Composable
@Preview
private fun LoginScreenPreview() {
    LoginScreen({ }, { }, hiltViewModel())
}