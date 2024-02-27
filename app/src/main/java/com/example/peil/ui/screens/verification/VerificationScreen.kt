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
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.screens.verification.state.VerificationEvent
import com.example.peil.ui.theme.GreyDark
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.filed.OtpCell
import com.example.peil.util.sharedPreferencesUser

@Composable
fun VerificationScreen(
    viewModel: VerificationViewModel,
    onLessonsListScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val state = viewModel.state.value
        if (state.successVerification) {
            sharedPreferencesUser(LocalContext.current).edit().putString("token", state.token).apply()
            onLessonsListScreen()
            viewModel.updateVerificationStatus()
        }
        Text(
            modifier = Modifier.padding(top = 30.dp).padding(horizontal = 16.dp).fillMaxWidth(),
            text = stringResource(id = R.string.message_with_code_has_been_sent),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            fontWeight = FontWeight.Bold
        )
        CodeInput(
            modifier = Modifier.padding(top = 20.dp),
            value = state.code.text,
            onValueChanged = { viewModel.createEvent(VerificationEvent.EnteringCode(it)) })
        Spacer(modifier = Modifier.weight(1F))
        LoginButton(
            modifier = Modifier.padding(bottom = 16.dp),
            textButton = R.string.continue_text,
            enabled = state.code.text.length == 6,
            onClick = { viewModel.createEvent(VerificationEvent.OnVerification) },
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
    VerificationScreen(hiltViewModel(), {})
}