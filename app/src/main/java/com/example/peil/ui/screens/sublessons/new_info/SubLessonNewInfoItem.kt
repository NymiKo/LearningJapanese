package com.example.peil.ui.screens.sublessons.new_info

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.image.SubLessonImage
import com.example.peil.ui.view_components.text.HeaderLessonText
import kotlinx.coroutines.launch

@Composable
fun SubLessonNewInfoScreen(
    subLessonItem: SubLessonModel,
    onCompleted: (completed: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .fillMaxSize()
    ) {
        HeaderLessonText(modifier = Modifier.padding(vertical = 8.dp), headerText = subLessonItem.header)
        SubLessonImage(subLessonImageUrl = subLessonItem.lessonImage)
        NewWordLesson(newWordText = subLessonItem.newWord)
        TranslationWordLesson(translationWordText = subLessonItem.translationWord)
        Spacer(modifier = Modifier.weight(1f))
        ButtonNextSubLesson(onCompleted = onCompleted::invoke)
    }
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
    onCompleted: (completed: Boolean) -> Unit
) {
    LoginButton(
        textButton = R.string.continue_text,
        horizontalPadding = 0.dp,
        onClick = {
            onCompleted(true)
        }
    ) { }
}

@Preview
@Composable
private fun SubLessonNewInfoScreenPreview() {
    SubLessonNewInfoScreen(
        SubLessonModel(0, completed = mutableStateOf(false), type = 0),
        onCompleted = {}
    )
}