package com.example.peil.ui.view_components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.peil.ui.theme.baseBlue

@Composable
fun HeaderLessonText(modifier: Modifier = Modifier, headerText: String) {
    Text(
        modifier = modifier,
        text = headerText,
        fontWeight = FontWeight.Bold,
        color = baseBlue,
        fontSize = 14.sp
    )
}