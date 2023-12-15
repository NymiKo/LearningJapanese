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
import androidx.compose.ui.unit.dp
import com.example.peil.R
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.OutlinedLoginField
import com.example.peil.ui.view_components.TextLabel

@Composable
fun CreateAccountScreen() {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        BaseAppBar(title = R.string.create_account, imageVector = Icons.Default.ArrowBack)
        TextLabel(modifier = Modifier.padding(top = 30.dp), textLabel = R.string.email)
        OutlinedLoginField()
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.name)
        OutlinedLoginField()
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.password_min_char)
        OutlinedLoginField(password = true)
        LoginButton(textButton = R.string.registration)
    }
}

@Composable
@Preview
private fun CreateAccountScreenPreview() {
    CreateAccountScreen()
}