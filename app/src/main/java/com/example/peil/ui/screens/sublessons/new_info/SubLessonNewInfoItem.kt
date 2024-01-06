package com.example.peil.ui.screens.sublessons.new_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.view_components.LoginButton
import kotlinx.coroutines.launch

@Composable
fun SubLessonNewInfoScreen(subLessonItem: SubLessonModel, listState: LazyListState, index: Int) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        HeaderLesson(headerText = subLessonItem.header)
        NewWordLesson(newWordText = subLessonItem.newWord)
        TranslationWordLesson(translationWordText = subLessonItem.translationWord)
        Spacer(modifier = Modifier.weight(1f))
        ButtonNextSubLesson(listState = listState, index = index)
    }
}

@Composable
private fun HeaderLesson(modifier: Modifier = Modifier, headerText: String) {
    Text(
        text = headerText,
        fontWeight = FontWeight.Bold,
        color = Blue,
        fontSize = 14.sp
    )
}

@Composable
private fun NewWordLesson(modifier: Modifier = Modifier, newWordText: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = newWordText,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 20.sp
    )
}

@Composable
private fun TranslationWordLesson(modifier: Modifier = Modifier, translationWordText: String) {
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = translationWordText,
        color = GreyLight,
        fontSize = 14.sp
    )
}

@Composable
private fun ButtonNextSubLesson(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    index: Int
) {
    val coroutineScope = rememberCoroutineScope()
    LoginButton(
        textButton = R.string.continue_text,
        horizontalPadding = 0.dp,
        onClick = { coroutineScope.launch { listState.animateScrollToItem(index.plus(1)) } }) { }
}

@Preview
@Composable
private fun SubLessonNewInfoScreenPreview() {
    SubLessonNewInfoScreen(
        SubLessonModel(0, completed = false, type = 0),
        rememberLazyListState(),
        1
    )
}