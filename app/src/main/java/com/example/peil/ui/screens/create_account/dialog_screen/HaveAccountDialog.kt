package com.example.peil.ui.screens.create_account.dialog_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.peil.R
import com.example.peil.ui.navigation.Screens
import com.example.peil.ui.screens.create_account.CreateAccountViewModel
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.view_components.LoginButton

@Composable
fun HaveAccountDialog(navController: NavController, onDismissRequest: (isOpen: Boolean) -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest(false) }) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(5.dp)),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.account_already_exists),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.please_try_to_login),
                    color = MaterialTheme.colorScheme.secondary
                )
                LoginButton(textButton = R.string.sign_in, onClick = {
                    onDismissRequest(false)
                    navController.navigate(Screens.Login.route)
                }) {

                }
                Text(
                    modifier = Modifier.clickable { onDismissRequest(false) },
                    text = stringResource(id = R.string.no_thanks),
                    textDecoration = TextDecoration.Underline,
                    color = GreyLight,
                    fontSize = 12.sp
                )
            }
        }
    }
}