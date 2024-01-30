package com.example.peil.ui.screens.sublessons.finish_sentence

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.SubLessonsType
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.RedLight
import com.example.peil.ui.theme.backgroundIconGreenLight
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.theme.correctlyOptionGreen
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.icon.IconVolume
import com.example.peil.ui.view_components.text.HeaderLessonText

@Composable
fun SubLessonFinishSentenceItem(
    subLessonItem: SubLessonModel,
    onCompleted: (completed: Boolean) -> Unit
) {
    val answers = remember { mutableStateMapOf<Int, Boolean>() }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLessonText(modifier = Modifier.padding(16.dp), headerText = subLessonItem.header)
        SentenceSubLesson(
            sentence = subLessonItem.sentence
        )
        OptionButtons(
            variants = subLessonItem.options,
            correctOption = subLessonItem.correctOption,
            addAnswer = { key, value -> answers[key] = value },
            removeAnswer = { key -> answers.remove(key) }
        )
        Spacer(modifier = Modifier.weight(1F))
        AnimatedVisibility(
            visible = answers.values.size == 2
        ) {
            BottomCard(
                success = answers.filterValues { it }.size == 2,
                translationWord = subLessonItem.translationWord,
                remark = subLessonItem.remark,
                audio = subLessonItem.audio,
                onCompleted = { onCompleted(answers.filterValues { it }.size == 2) }
            )
        }
    }
}

@Composable
private fun SentenceSubLesson(
    modifier: Modifier = Modifier,
    sentence: Array<String>,
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
                    color = baseBlue
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OptionButtons(
    modifier: Modifier = Modifier,
    variants: Array<String>,
    correctOption: Array<String>,
    addAnswer: (key: Int, value: Boolean) -> Unit,
    removeAnswer: (key: Int) -> Unit
) {

    var countAnswer by remember { mutableIntStateOf(0) }
    var enabledButton by remember { mutableStateOf(true) }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        variants.forEachIndexed { index, text ->
            var checkSuccess by remember { mutableStateOf(false) }
            var checkError by remember { mutableStateOf(false) }
            var activeButton by remember { mutableStateOf(false) }

            Button(
                onClick = {
                    activeButton = !activeButton
                    if (correctOption.contains(text) && activeButton) {
                        checkSuccess = true
                        addAnswer(index, true)
                    } else if (!correctOption.contains(text) && activeButton) {
                        checkError = true
                        addAnswer(index, false)
                    }
                    if (activeButton) countAnswer += 1
                    else {
                        countAnswer -= 1
                        checkSuccess = false
                        checkError = false
                        removeAnswer(index)
                    }
                    if (countAnswer == 2) {
                        enabledButton = false
                    }
                },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        checkSuccess && countAnswer == 2 -> correctlyOptionGreen
                        checkError && countAnswer == 2 -> Color.Red
                        else -> MaterialTheme.colorScheme.primary
                    },
                    disabledContainerColor = when {
                        checkSuccess && countAnswer == 2 -> correctlyOptionGreen
                        checkError && countAnswer == 2 -> Color.Red
                        else -> GreyLightBD
                    }
                ),
                enabled = enabledButton,
                border = if (activeButton) BorderStroke(1.dp, baseBlue) else null
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
}

@Composable
private fun BottomCard(
    success: Boolean,
    translationWord: String,
    remark: String,
    audio: String,
    onCompleted: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconWithText(success = success)
            Text(
                text = stringResource(id = R.string.answer),
                fontSize = 12.sp,
                color = GreyLightBD
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1F),
                    text = remark,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                if (audio.isNotEmpty()) {
                    IconVolume(audio = audio)
                }
            }
            Text(text = translationWord, fontSize = 14.sp, color = GreyLight)
            LoginButton(
                textButton = R.string.continue_text,
                containerColor = if (success) correctlyOptionGreen else Color.Red,
                horizontalPadding = 0.dp,
                onClick = {
                    onCompleted()
                }
            ) {}
        }
    }
}

@Composable
private fun IconWithText(success: Boolean) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(
                    if (success) backgroundIconGreenLight else RedLight,
                    shape = CircleShape
                )
                .padding(3.dp),
            imageVector = if (success) Icons.Default.Check else Icons.Default.Close,
            contentDescription = "",
            tint = if (success) correctlyOptionGreen else Color.Red
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = if (success) R.string.correctly else R.string.wrong),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (success) correctlyOptionGreen else Color.Red
        )
    }
}

@Preview
@Composable
private fun SubLessonFinishSentenceItemPreview() {
    SubLessonFinishSentenceItem(
        subLessonItem = SubLessonModel(idSubLesson = 0, type = SubLessonsType.SubLessonFinishSentenceItem),
        onCompleted = {}
    )
}