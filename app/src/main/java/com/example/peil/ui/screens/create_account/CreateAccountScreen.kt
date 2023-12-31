package com.example.peil.ui.screens.create_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peil.R
import com.example.peil.ui.view_components.BaseAppBar
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.OutlinedLoginField
import com.example.peil.ui.view_components.text.TextLabel

@Composable
fun CreateAccountScreen(
    onRegistrationClick: () -> Unit,
    showHaveAccountDialog: () -> Unit,
    onBack: () -> Unit,
    viewModel: CreateAccountViewModel,
    email: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val state = viewModel.state.observeAsState()

        BaseAppBar(
            title = R.string.create_account,
            imageVector = Icons.Default.ArrowBack
        ) { onBack() }
        TextLabel(modifier = Modifier.padding(top = 30.dp), textLabel = R.string.email)
        OutlinedLoginField(value = viewModel.email, valueChange = viewModel::updateEmail)
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.name)
        OutlinedLoginField(value = viewModel.nickname, valueChange = viewModel::updateNickname)
        TextLabel(modifier = Modifier.padding(top = 16.dp), textLabel = R.string.password_min_char)
        OutlinedLoginField(value = viewModel.password, password = true, valueChange = viewModel::updatePassword)
        LoginButton(modifier = Modifier.padding(top = 30.dp), textButton = R.string.registration, onClick = viewModel::createAccount) {
            if (state.value is CreateAccountUiState.LOADING) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            }
        }
        checkState(state = state.value, showHaveAccountDialog::invoke)
    }
}
@Composable
private fun checkState(state: CreateAccountUiState?, showHaveAccountDialog: () -> Unit) {
    when(state) {
        is CreateAccountUiState.LOADING -> {

        }
        is CreateAccountUiState.SUCCESS -> {

        }
        is CreateAccountUiState.HAVE_ACCOUNT -> {
            showHaveAccountDialog()
        }
        is CreateAccountUiState.ERROR -> {

        }

        else -> {}
    }
}

//@Composable
//@Preview
//private fun CreateAccountScreenPreview() {
//    CreateAccountScreen(
//        rememberNavController(),
//        CreateAccountViewModel(CreateAccountRepositoryImpl(CreateAccountService)),
//        ""
//    )
//}