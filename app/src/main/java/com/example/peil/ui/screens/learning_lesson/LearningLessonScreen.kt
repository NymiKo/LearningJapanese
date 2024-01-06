package com.example.peil.ui.screens.learning_lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.screens.sublessons.choosing_option.SubLessonChoosingOptionItem
import com.example.peil.ui.screens.sublessons.new_info.SubLessonNewInfoScreen
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.GreyLight

@Composable
fun LearningLessonScreen() {

    val lessonsList = listOf(
        SubLessonModel(
            idSubLesson = 0,
            header = "Смотри-ка, кое-что новенькое!",
            newWord = "Konnichiwa / こんにちは",
            translationWord = "Здравствуйте",
            completed = false,
            type = 0
        ),
        SubLessonModel(
            idSubLesson = 1,
            header = "Выбери слово, которое переводится как: \"Привет\".",
            newWord = "Konnichiwa / こんにちは",
            translationWord = "Здравствуйте",
            completed = false,
            options = arrayOf("Konnichiwa", "Arigatou"),
            correctOption = "Konnichiwa",
            type = 1
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar()
        PagerLesson(lessonsList = lessonsList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar() {
    TopAppBar(
        title = {
            LinearProgressIndicator(
                progress = 0.2F,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(5.dp)
                    .clip(RoundedCornerShape(10.dp)),
                trackColor = GreyLight,
                color = Blue
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = Blue
            )
        }
    )
}

@Composable
private fun PagerLesson(lessonsList: List<SubLessonModel>) {

    val listState = rememberLazyListState()

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false,
        state = listState
    ) {
        itemsIndexed(lessonsList) {index, subLesson ->
            Box(modifier = Modifier.fillParentMaxSize()) {
                when(subLesson.type) {
                    0 -> SubLessonNewInfoScreen(subLesson, listState, index)
                    1 -> SubLessonChoosingOptionItem(subLesson)
                    else -> SubLessonNewInfoScreen(subLesson, listState, index)
                }
            }
        }
    }
}

@Preview
@Composable
private fun LearningLessonScreenPreview() {
    LearningLessonScreen()
}