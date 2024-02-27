package com.example.peil.ui.screens.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peil.R
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.filed.OtpCell

@Composable
fun VerificationScreen(
    onLessonsListScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var code by remember {
            mutableStateOf("")
        }
        CodeInput(
            modifier = Modifier.padding(top = 40.dp),
            value = code,
            onValueChanged = { code = it })
        Spacer(modifier = Modifier.weight(1F))
        LoginButton(
            modifier = Modifier.padding(bottom = 16.dp),
            textButton = R.string.continue_text,
            onClick = { },
            content = { }
        )
    }
}

@Composable
private fun CodeInput(
    modifier: Modifier = Modifier,
    length: Int = 6,
    value: String = "",
    onValueChanged: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .size(0.dp)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = {
            if (it.length <= length) {
                if (it.all { c -> c in '0'..'9' }) {
                    onValueChanged(it)
                }
                if (it.length >= length) {
                    keyboard?.hide()
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(length) {
            OtpCell(
                modifier = modifier
                    .size(width = 45.dp, height = 60.dp)
                    .clip(MaterialTheme.shapes.large)
                    .background(White)
                    .onFocusChanged {
                        focusRequester.requestFocus()
                    }
                    .clickable {
                        keyboard?.show()
                    },
                value = value.getOrNull(it)?.toString() ?: "",
                isCursorVisible = value.length == it
            )
            if (it != length - 1) Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Preview
@Composable
private fun VerificationScreenPreview() {
    VerificationScreen({})
}