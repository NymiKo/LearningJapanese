package com.example.peil.ui.screens.lessons_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.peil.R
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.Green
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.White

@Composable
fun LessonsListScreen(
    modifier: Modifier = Modifier,
    viewModel: LessonsListViewModel
) {
    Column(modifier) {
        TopAppBar()
        LearningProgress(progressValue = 25F)
        LessonsListComponent(lessonsList = viewModel.lessonsList)
    }
    Log.e("LESSONS", viewModel.lessonsList.toString())
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LearningProgress(modifier: Modifier = Modifier, progressValue: Float) {
    Slider(
        modifier = modifier.fillMaxWidth(),
        value = progressValue,
        onValueChange = {},
        valueRange = 0F..100F,
        track = {
            SliderDefaults.Track(
                modifier = Modifier.scale(scaleX = 1f, scaleY = 2f),
                colors = SliderDefaults.colors(
                    activeTrackColor = Green,
                    inactiveTrackColor = GreyLight
                ),
                sliderPositions = it
            )
        },
        thumb = { position ->
            Card(
                modifier = Modifier
                    .width(35.dp)
                    .height(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Green,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "${progressValue.toInt()}%",
                    fontSize = 10.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@Composable
private fun LessonsListComponent(
    modifier: Modifier = Modifier,
    lessonsList: List<LessonModel> = listOf()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(lessonsList) {index, lesson ->
            LessonItem(lesson = lesson, lastItem = index == lessonsList.lastIndex)
        }
    }
}


@Composable
private fun LessonItem(
    modifier: Modifier = Modifier,
    lesson: LessonModel,
    lastItem: Boolean
) {
    Row(
        modifier = modifier,
    ) {
        ImageLessonAndDivider(lastItem = lastItem, imageLesson = lesson.image)

        Row(
            modifier = Modifier
                .height(74.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = lesson.header,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.CloudDownload,
                contentDescription = stringResource(id = R.string.download_lesson),
                tint = Blue
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLessonAndDivider(modifier: Modifier = Modifier, lastItem: Boolean, imageLesson: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(70.dp)
                .border(4.dp, GreyLight, CircleShape)
                .padding(4.dp)
        ) {
            GlideImage(
                model = imageLesson,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .border(4.dp, White, CircleShape)
                    .clip(CircleShape)
            )
        }

        if(!lastItem) {
            Divider(
                color = GreyLight,
                modifier = Modifier
                    .height(20.dp)
                    .width(3.dp)
            )
        }
    }
}

@Composable
@Preview
private fun LessonsListScreenPreview() {
    Column {
        LessonsListScreen(modifier = Modifier.weight(1f), hiltViewModel())
    }
}