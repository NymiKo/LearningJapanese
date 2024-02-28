package com.example.peil.ui.view_components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.RedLight

@Composable
fun AuthorizationErrorMessage(modifier: Modifier = Modifier, errorMessage: Int) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(RedLight, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = 16.dp),
        text = stringResource(id = errorMessage),
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}