package com.example.peil.ui.screens.learning_lesson

import android.util.Log
import android.util.LogPrinter
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.screens.sublessons.choosing_option.SubLessonChoosingOptionItem
import com.example.peil.ui.screens.sublessons.new_info.SubLessonNewInfoScreen
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.GreyLight

@Composable
fun LearningLessonScreen(
    viewModel: LearningLessonViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(progress = viewModel.progress)
        PagerLesson(lessonsList = viewModel.subLessons, onCompleted = { item, completed -> viewModel.updateCompleted(item, completed) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(progress: Float) {
    TopAppBar(
        title = {
            LinearProgressIndicator(
                progress = progress,
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
private fun PagerLesson(lessonsList: List<SubLessonModel>, onCompleted: (item: SubLessonModel, competed: Boolean) -> Unit) {

    val listState = rememberLazyListState()

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false,
        state = listState
    ) {
        itemsIndexed(lessonsList) {index, subLesson ->
            Box(modifier = Modifier.fillParentMaxSize()) {
                when(subLesson.type) {
                    0 -> SubLessonNewInfoScreen(subLesson, listState, index, onCompleted = { completed -> onCompleted(lessonsList[index], completed) })
                    1 -> SubLessonChoosingOptionItem(subLesson, onCompleted = { completed -> onCompleted(lessonsList[index], completed) })
                    else -> SubLessonNewInfoScreen(subLesson, listState, index, onCompleted = { completed -> onCompleted(lessonsList[index], completed) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun LearningLessonScreenPreview() {
    LearningLessonScreen(hiltViewModel())
}