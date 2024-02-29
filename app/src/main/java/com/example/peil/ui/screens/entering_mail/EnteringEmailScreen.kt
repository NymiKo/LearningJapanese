package com.example.peil.ui.screens.entering_mail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.screens.entering_mail.state.EnteringEmailEvent
import com.example.peil.ui.theme.GreyDark
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.filed.OutlinedLoginField
import com.example.peil.ui.view_components.text.AuthorizationErrorMessage

@Composable
fun EnteringEmailScreen(
    viewModel: EnteringEmailViewModel,
    onVerificationScreen: (idUser: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val state = viewModel.state.value

        if (state.correctEmail) {
            onVerificationScreen(state.idUser)
            viewModel.updateCorrectEmailStatus()
        }

        if (state.isError) {
            AuthorizationErrorMessage(
                modifier = Modifier.padding(top = 16.dp),
                errorMessage = state.errorMessage
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.entering_email),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = GreyDark,
            fontWeight = FontWeight.Bold
        )
        OutlinedLoginField(
            modifier = Modifier.padding(top = 8.dp),
            value = state.email.text,
            error = state.email.isError,
            valueChange = { viewModel.createEvent(EnteringEmailEvent.EnteringEmail(it)) }
        )
        Spacer(modifier = Modifier.weight(1F))
        LoginButton(
            modifier = Modifier.padding(bottom = 16.dp),
            textButton = R.string.continue_text,
            onClick = { viewModel.createEvent(EnteringEmailEvent.SendingEmail) },
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
private fun EnteringEmailScreenPreview() {
    EnteringEmailScreen(hiltViewModel(), {})
}