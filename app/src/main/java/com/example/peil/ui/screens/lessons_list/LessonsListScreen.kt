package com.example.peil.ui.screens.lessons_list

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R

@Composable
fun LessonsListScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        TopAppBar()
        LessonsListComponent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lessons_list),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = { NavigationIconTopAppBar() },
    )
}

@Composable
private fun NavigationIconTopAppBar() {
    Image(
        painter = painterResource(id = R.drawable.flag_japan),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(35.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
    )
}

@Composable
private fun LessonsListComponent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            LessonItemContainer()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LessonItemContainer(
    modifier: Modifier = Modifier,
) {
    var expandedState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        LessonItemContent(expandedState = expandedState)
    }
}

@Composable
private fun LessonItemContent(modifier: Modifier = Modifier, expandedState: Boolean) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Lesson Name", fontWeight = FontWeight.Bold)
        Text(text = "Lesson time", fontSize = 14.sp)
        if (expandedState) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(top = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Text(text = "Image")
            }
        }
    }
}

@Composable
@Preview
private fun LessonsListScreenPreview() {
    Column {
        LessonsListScreen(modifier = Modifier.weight(1f))
    }
}