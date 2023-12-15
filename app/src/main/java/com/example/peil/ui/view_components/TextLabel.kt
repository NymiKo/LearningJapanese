package com.example.peil.ui.view_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.GreyLight

@Composable
fun TextLabel(modifier: Modifier = Modifier, textLabel: Int) {
    Text(
        modifier = modifier
            .padding(horizontal = 16.dp),
        text = stringResource(id = textLabel),
        fontWeight = FontWeight.Bold,
        color = GreyLight
    )
}