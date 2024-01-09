package com.example.peil.ui.screens.sublessons.finish_sentence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.theme.correctlyOptionGreen
import com.example.peil.ui.view_components.text.HeaderLessonText

@Composable
fun SubLessonFinishSentenceItem(
    subLessonItem: SubLessonModel,
    listState: LazyListState,
    index: Int,
    onCompleted: (completed: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLessonText(modifier = Modifier.padding(16.dp), headerText = subLessonItem.header)
        SentenceSubLesson(
            sentence = subLessonItem.sentence,
            text = "",
            error = false,
            success = false
        )
        OptionButtons(
            variants = subLessonItem.options,
            correctOption = subLessonItem.correctOption,
            onTextChange = {},
            onSuccess = {},
            onError = {}
        )
    }
}

@Composable
private fun SentenceSubLesson(
    modifier: Modifier = Modifier,
    sentence: Array<String>,
    text: String,
    error: Boolean,
    success: Boolean
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        sentence.forEach {
            if (it.isNotEmpty()) {
                Text(text = it, color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp)
            } else {
                Text(
                    text = "    ",
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp,
                    color = if (success) correctlyOptionGreen else if (error) Color.Red else baseBlue
                )
            }
        }
    }
}

@Composable
private fun OptionButtons(
    modifier: Modifier = Modifier,
    variants: Array<String>,
    correctOption: Array<String>,
    onTextChange: (text: String) -> Unit,
    onSuccess: (success: Boolean) -> Unit,
    onError: (error: Boolean) -> Unit
) {

    var countAnswer by remember { mutableIntStateOf(0) }
    var enabledButton by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            variants.forEach { text ->
                var checkSuccess by remember { mutableStateOf(false) }
                var checkError by remember { mutableStateOf(false) }

                Button(
                    onClick = {
                        if (correctOption.contains(text)) {
                            checkSuccess = true
                            onSuccess(true)
                        } else {
                            checkError = true
                            onError(true)
                        }
                        countAnswer += 1
                        onTextChange(text)
                        if (countAnswer == 2) {
                            enabledButton = false
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (checkSuccess) correctlyOptionGreen else if (checkError) Color.Red else MaterialTheme.colorScheme.primary,
                        disabledContainerColor = if (checkSuccess) correctlyOptionGreen else if (checkError) Color.Red else GreyLightBD
                    ),
                    enabled = enabledButton
                ) {
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SubLessonFinishSentenceItemPreview() {
    SubLessonFinishSentenceItem(
        subLessonItem = SubLessonModel(idSubLesson = 0, type = 0),
        listState = rememberLazyListState(),
        index = 0,
        onCompleted = {}
    )
}