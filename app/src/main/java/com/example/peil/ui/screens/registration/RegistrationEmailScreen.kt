package com.example.peil.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.view_components.AlreadyHaveAccountText
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.OutlinedLoginField
import com.example.peil.ui.view_components.TextLabel

@Composable
fun RegistrationEmailScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        BaseAppBar(imageVector = Icons.Default.ArrowBack)
        Header()
        DescriptionText()
        EmailField()
        LoginButton(textButton = R.string.continue_text)
        AlreadyHaveAccountText(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp))
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(1.dp, GreyLight, CircleShape),
            painter = painterResource(id = R.drawable.flag_japan),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = R.string.konnichiwa),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    }
}

@Composable
private fun DescriptionText(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        text = stringResource(id = R.string.sign_up_and_start_learning_japanese),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun EmailField() {
    TextLabel(modifier = Modifier.padding(top = 40.dp), textLabel = R.string.email)
    OutlinedLoginField()
}

@Composable
@Preview
private fun RegistrationEmailScreenPreview() {
    RegistrationEmailScreen()
}