package com.example.peil.ui.view_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.White

@Composable
fun LoginButton(modifier: Modifier = Modifier, textButton: Int) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp),
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = stringResource(id = textButton),
            color = White
        )
    }
}