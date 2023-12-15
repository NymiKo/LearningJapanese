package com.example.peil.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.White

@Composable
fun LoginScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        TopAppBar()
        LoadingScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text(modifier = Modifier.padding(start = 16.dp), text = stringResource(id = R.string.sign_in), fontWeight = FontWeight.Bold, fontSize = 18.sp) },
        navigationIcon = { Icon(modifier = Modifier.padding(start = 16.dp), imageVector = Icons.Default.ArrowBack, contentDescription = null) }
    )
}

@Composable
private fun LoadingScreenContent() {
    Column {
        Header()
        FieldItem(modifier = Modifier.padding(top = 40.dp),textLabel = R.string.email)
        FieldItem(modifier = Modifier.padding(top = 40.dp), textLabel = R.string.password, password = true)
        ForgotPasswordText()
        LoginButton()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FieldItem(modifier: Modifier = Modifier, textLabel: Int, password: Boolean = false) {
    var text by remember { mutableStateOf("") }
    var passwordShow by remember { mutableStateOf(false) }

    Text(
        modifier = modifier
            .padding(horizontal = 16.dp),
        text = stringResource(id = textLabel),
        fontWeight = FontWeight.Bold,
        color = GreyLight)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        singleLine = true,
        value = text,
        onValueChange = { text = it },
        trailingIcon = { if (password) IconButton(onClick = { passwordShow = !passwordShow }) {
            Icon(imageVector = if (passwordShow) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility, contentDescription = null)
        } },
        visualTransformation = if (password) if (!passwordShow) PasswordVisualTransformation() else VisualTransformation.None else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.secondary
        )
    )
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
        onClick = {  }
    )
}

@Composable
private fun LoginButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp),
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue
        )
    ) {
        Text(modifier = Modifier.padding(vertical = 4.dp), text = stringResource(id = R.string.sign_in), color = White)
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    LoginScreen()
}