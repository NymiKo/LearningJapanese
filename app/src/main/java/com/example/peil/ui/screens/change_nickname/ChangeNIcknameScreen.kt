package com.example.peil.ui.screens.change_nickname

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue

@Composable
fun ChangeNicknameScreen(
    viewModel: ChangeNicknameViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.state.value

    if (state.successChangeNickname) {
        onBack()
    }

    Scaffold(
        topBar = { TopAppBar(changeNickname = viewModel::changeNickname, onBack = onBack::invoke) }
    ) {
        TextField(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.nickname.text,
            onValueChange = { newValue -> viewModel.enteringNickname(newValue) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = baseBlue,
                focusedIndicatorColor = baseBlue,
                unfocusedIndicatorColor = baseBlue
            ),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    changeNickname: () -> Unit,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.username),
                fontWeight = FontWeight.Bold,
                color = White
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack::invoke) {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = baseBlue
        ),
        actions = {
            TextButton(onClick = changeNickname::invoke) {
                Text(text = stringResource(id = R.string.done).uppercase(), color = White)
            }
        }
    )
}

@Preview
@Composable
private fun ChangeNicknameScreenPreview() {
    ChangeNicknameScreen(hiltViewModel(), {})
}