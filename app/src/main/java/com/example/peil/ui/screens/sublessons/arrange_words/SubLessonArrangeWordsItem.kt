package com.example.peil.ui.screens.sublessons.arrange_words

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.SubLessonsType
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.ActiveButtonGrey
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.RedLight
import com.example.peil.ui.theme.backgroundIconGreenLight
import com.example.peil.ui.theme.correctlyOptionGreen
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.image.SubLessonImage
import com.example.peil.ui.view_components.text.HeaderLessonText

@Composable
fun SubLessonArrangeWordsItem(
    subLessonItem: SubLessonModel,
    onCompleted: (completed: Boolean) -> Unit
) {
    val selectedWords = remember { mutableStateListOf<String>() }
    val lastRemovedWord = remember { mutableStateOf("") }
    var finish by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLessonText(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            headerText = subLessonItem.header
        )
        FrameForWords(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            wordsList = selectedWords,
            finish = finish,
            correctOptions = subLessonItem.correctOption,
            removeWord = { word ->
                selectedWords.remove(word)
                lastRemovedWord.value = word
            }
        )
        OptionButtons(
            variants = subLessonItem.options,
            addWord = { word ->
                if (!selectedWords.contains(word)) selectedWords.add(word)
                finish = selectedWords.size == subLessonItem.correctOption.size
            },
            lastRemovedWord = lastRemovedWord
        )
        Spacer(modifier = Modifier.weight(1F))
        AnimatedVisibility(
            visible = finish
        ) {
            BottomCard(
                success = selectedWords.zip(subLessonItem.correctOption).all { (a, b) -> a == b },
                correctOption = subLessonItem.correctOption,
                translationWord = subLessonItem.translationWord,
                onCompleted = { onCompleted(selectedWords.zip(subLessonItem.correctOption).all { (a, b) -> a == b }) }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FrameForWords(
    modifier: Modifier = Modifier,
    wordsList: List<String>,
    finish: Boolean,
    correctOptions: Array<String>,
    removeWord: (word: String) -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, GreyLight, RoundedCornerShape(10.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (wordsList.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = stringResource(id = R.string.choose_the_words),
                textAlign = TextAlign.Center,
                color = GreyLightBD
            )
        } else {
            wordsList.forEachIndexed { index, word ->

                Log.e("SIZE", finish.toString())

                Button(
                    onClick = {
                        removeWord(word)
                    },
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp
                    ),
                    enabled = !finish,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = if (finish) if (correctOptions[index] == word) correctlyOptionGreen else Color.Red else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = word,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun OptionButtons(
    modifier: Modifier = Modifier,
    variants: Array<String>,
    lastRemovedWord: MutableState<String>,
    addWord: (word: String) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        variants.forEachIndexed { _, word ->
            var activeButton by remember { mutableStateOf(false) }
            if (word == lastRemovedWord.value) {
                activeButton = false
                lastRemovedWord.value = ""
            }

            Button(
                onClick = {
                    activeButton = true
                    addWord(word)
                },
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = if (activeButton) 0.dp else 2.dp
                ),
                border = if (activeButton) BorderStroke(1.dp, GreyLight) else null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (activeButton) ActiveButtonGrey else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = word,
                    color = if (activeButton) ActiveButtonGrey else MaterialTheme.colorScheme.secondary,
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
    correctOption: Array<String>,
    translationWord: String,
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
            Text(
                text = correctOption.joinToString(" "),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Text(text = translationWord, fontSize = 14.sp, color = GreyLight)
            LoginButton(
                textButton = R.string.continue_text,
                containerColor = if (success) correctlyOptionGreen else Color.Red,
                horizontalPadding = 0.dp,
                onClick = {
                    onCompleted()
                },
                content = {}
            )
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
private fun SubLessonArrangeWordsItemPreview() {
    SubLessonArrangeWordsItem(SubLessonModel(idSubLesson = 0, type = SubLessonsType.SubLessonArrangeWordsItem), onCompleted = {})
}