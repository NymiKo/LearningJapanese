package com.example.peil.ui.view_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.White

@Composable
fun LoginButton(modifier: Modifier = Modifier, textButton: Int, onClick: () -> Unit, content: @Composable () -> Unit) = Button(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
    onClick = { onClick() },
    colors = ButtonDefaults.buttonColors(
        containerColor = Blue
    )
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth(),
            text = stringResource(id = textButton),
            color = White,
            textAlign = TextAlign.Center
        )
        content()
    }
}