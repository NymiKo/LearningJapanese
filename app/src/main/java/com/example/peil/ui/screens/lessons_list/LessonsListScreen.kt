package com.example.peil.ui.screens.lessons_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircleOutline
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.peil.R
import com.example.peil.ui.screens.lessons_list.data.model.LessonCategory
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import com.example.peil.ui.theme.Green
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.theme.correctlyOptionGreen

@Composable
fun LessonsListScreen(
    modifier: Modifier = Modifier,
    onLearningLesson: (idLesson: Int) -> Unit,
    viewModel: LessonsListViewModel
) {
    Column(modifier) {
        TopAppBar()
        LearningProgress(progressValue = viewModel.progress)
        LessonsListComponent(
            lessonsList = viewModel.lessonsList,
            onLearningLesson = onLearningLesson::invoke
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LearningProgress(modifier: Modifier = Modifier, progressValue: Float) {
    Slider(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        value = progressValue,
        onValueChange = {},
        valueRange = 0.0F..1.0F,
        track = {
            SliderDefaults.Track(
                modifier = Modifier.scale(scaleX = 1f, scaleY = 2f),
                colors = SliderDefaults.colors(
                    activeTrackColor = Green,
                    inactiveTrackColor = GreyLightBD
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
                    text = "${(progressValue * 100).toInt()}%",
                    fontSize = 10.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LessonsListComponent(
    modifier: Modifier = Modifier,
    onLearningLesson: (idLesson: Int) -> Unit,
    lessonsList: List<LessonCategory>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        lessonsList.forEach { lessonWithCategory ->
            stickyHeader {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                    text = stringResource(id = R.string.chapter, lessonWithCategory.chapter),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(bottom = 8.dp),
                    text = stringResource(
                        id = R.string.lessons_completed,
                        lessonWithCategory.lessonsList.filter { it.completed }.size,
                        lessonWithCategory.lessonsList.size
                    ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            itemsIndexed(lessonWithCategory.lessonsList) { index, lesson ->
                LessonItem(
                    lesson = lesson,
                    lastItem = index == lessonWithCategory.lessonsList.lastIndex,
                    onLearningLesson = onLearningLesson::invoke
                )
            }
        }
    }
}


@Composable
private fun LessonItem(
    modifier: Modifier = Modifier,
    onLearningLesson: (idLesson: Int) -> Unit,
    lesson: LessonModel,
    lastItem: Boolean
) {
    Row(
        modifier = modifier.clickable { onLearningLesson(lesson.idLesson) },
    ) {
        ImageLessonAndDivider(
            lastItem = lastItem,
            imageLesson = lesson.image,
            completed = lesson.completed
        )

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
                tint = baseBlue
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLessonAndDivider(
    modifier: Modifier = Modifier,
    lastItem: Boolean,
    imageLesson: String,
    completed: Boolean
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .size(70.dp)
                .border(4.dp, if (completed) Green else GreyLightBD, CircleShape)
                .padding(4.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            GlideImage(
                model = imageLesson,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .border(4.dp, MaterialTheme.colorScheme.background, CircleShape)
                    .clip(CircleShape)
            )

            if (completed) {
                Icon(
                    modifier = Modifier.size(20.dp).border(1.5.dp, MaterialTheme.colorScheme.background, CircleShape).background(Green, CircleShape).padding(2.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }

        if (!lastItem) {
            Divider(
                color = if (completed) Green else GreyLightBD,
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
        LessonsListScreen(modifier = Modifier.weight(1f), onLearningLesson = {}, hiltViewModel())
    }
}