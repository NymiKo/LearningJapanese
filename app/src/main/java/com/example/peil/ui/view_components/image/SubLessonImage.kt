package com.example.peil.ui.view_components.image

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.peil.ui.theme.GreyLightBD

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SubLessonImage(modifier: Modifier = Modifier, subLessonImageUrl: String) {
    if (subLessonImageUrl.isNotEmpty()) {
        GlideImage(
            modifier = modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = GreyLightBD, shape = RoundedCornerShape(10.dp)),
            model = subLessonImageUrl,
            contentDescription = null
        )
    }
}