package com.example.peil.ui.screens.learning_lesson

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.screens.sublessons.arrange_words.SubLessonArrangeWordsItem
import com.example.peil.ui.screens.sublessons.choosing_option.SubLessonChoosingOptionItem
import com.example.peil.ui.screens.sublessons.finish_sentence.SubLessonFinishSentenceItem
import com.example.peil.ui.screens.sublessons.hint.SubLessonHintItem
import com.example.peil.ui.screens.sublessons.new_info.SubLessonNewInfoScreen
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.baseBlue
import kotlinx.coroutines.launch

@Composable
fun LearningLessonScreen(
    viewModel: LearningLessonViewModel,
    idLesson: Int,
    onLessonCompletionScreen: (idLesson: Int) -> Unit,
    showCancelLessonDialog: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(progress = viewModel.progress)
        PagerLesson(
            subLessonsList = viewModel.subLessons,
            idLesson = idLesson,
            onCompleted = { item, completed -> viewModel.updateCompleted(item, completed) },
            onLessonCompletionScreen = onLessonCompletionScreen::invoke
        )
        BackHandler(enabled = true) {
            showCancelLessonDialog()
        }
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
                trackColor = GreyLightBD,
                color = baseBlue
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = baseBlue
            )
        }
    )
}

@Composable
private fun PagerLesson(
    subLessonsList: List<SubLessonModel>,
    idLesson: Int,
    onCompleted: (item: SubLessonModel, competed: Boolean) -> Unit,
    onLessonCompletionScreen: (idLesson: Int) -> Unit
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        userScrollEnabled = false,
        state = listState
    ) {
        itemsIndexed(subLessonsList) { index, subLesson ->
            Box(modifier = Modifier.fillParentMaxSize()) {
                when (subLesson.type) {
                    0 -> SubLessonNewInfoScreen(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })

                    1, 2 -> SubLessonChoosingOptionItem(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })

                    3 -> SubLessonFinishSentenceItem(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })

                    4 -> SubLessonArrangeWordsItem(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })

                    5 -> SubLessonHintItem(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })

                    else -> SubLessonNewInfoScreen(subLesson, onCompleted = { completed ->
                        onCompleted(subLessonsList[index], completed)
                        if (index != subLessonsList.lastIndex) {
                            coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) }
                        } else {
                            onLessonCompletionScreen(idLesson)
                        }
                    })
                }
            }
        }
    }
}

@Preview
@Composable
private fun LearningLessonScreenPreview() {
    LearningLessonScreen(hiltViewModel(), 0, {}, {})
}