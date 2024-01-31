package com.example.peil.ui.screens.lessons_list

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerComponent(modifier: Modifier = Modifier) {
    val repeatItem = 6
    LazyColumn(modifier = modifier) {
        repeat(repeatItem) {
            item {
                ShimmerAnimation(it == repeatItem - 1)
            }
        }
    }
}

@Composable
private fun ShimmerItem(brush: Brush, lastIndex: Boolean) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        Column {
            Spacer(modifier = Modifier
                .padding(vertical = 6.dp)
                .size(70.dp)
                .clip(CircleShape)
                .background(brush = brush)
            )
            if (!lastIndex) {
                Spacer(modifier = Modifier
                    .height(20.dp)
                    .width(3.dp)
                    .background(brush = brush)
                    .align(Alignment.CenterHorizontally)
                )
            }
        }
        Row(
            modifier = Modifier.height(74.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier
                .padding(start = 8.dp)
                .weight(1F)
                .height(20.dp)
                .background(brush = brush))
        }
    }
}

@Composable
private fun ShimmerAnimation(lastIndex: Boolean) {

    val ShimmerColorShades = listOf(
        Color.LightGray.copy(0.9F),
        Color.LightGray.copy(0.2F),
        Color.LightGray.copy(0.9F)
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1000F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10F, 10F),
        end = Offset(translateAnimation, translateAnimation)
    )

    ShimmerItem(brush = brush, lastIndex = lastIndex)
}