package com.example.peil.ui.view_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedLoginField(
    modifier: Modifier = Modifier,
    value: String? = "",
    password: Boolean = false,
    error: Boolean = false,
    valueChange: (String) -> Unit
) {
    var passwordShow by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        singleLine = true,
        value = value ?: "",
        onValueChange = { valueChange(it) },
        trailingIcon = {
            if (password) IconButton(onClick = { passwordShow = !passwordShow }) {
                Icon(
                    imageVector = if (passwordShow) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (password) if (!passwordShow) PasswordVisualTransformation() else VisualTransformation.None else VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.secondary
        ),
        isError = error
    )
}