package com.example.peil.ui.screens.sublessons.listen_and_choose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.backgroundIconGreenLight
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.theme.correctlyOptionGreen
import com.example.peil.ui.view_components.AudioPlayer
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.icon.IconVolume
import com.example.peil.ui.view_components.image.SubLessonImage
import com.example.peil.ui.view_components.text.HeaderLessonText

@Composable
fun SubLessonListenAndChooseItem(
    subLessonItem: SubLessonModel,
    onCompleted: (completed: Boolean) -> Unit
) {

    var textSelectedOption by remember { mutableStateOf("             ") }
    var success by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLessonText(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            headerText = subLessonItem.header
        )

        SubLessonImage(modifier = Modifier.padding(horizontal = 16.dp), subLessonImageUrl = subLessonItem.lessonImage)

        AudioPlayer(audio = subLessonItem.audio)

        OptionButtons(
            variants = subLessonItem.options,
            correctOption = subLessonItem.correctOption,
            type = subLessonItem.type,
            onTextChange = { text ->
                textSelectedOption = text
            },
            onSuccess = { success = it },
            onError = { error = it }
        )
        Spacer(modifier = Modifier.weight(1F))

        AnimatedVisibility(
            visible = success || error
        ) {
            BottomCard(
                success = success,
                correctOption = subLessonItem.correctOption,
                translationWord = subLessonItem.translationWord,
                remark = subLessonItem.remark,
                onCompleted = { onCompleted(success) }
            )
        }
    }
}

@Composable
private fun OptionButtons(
    modifier: Modifier = Modifier,
    variants: Array<String>,
    correctOption: Array<String>,
    type: SubLessonsType,
    onTextChange: (text: String) -> Unit,
    onSuccess: (success: Boolean) -> Unit,
    onError: (error: Boolean) -> Unit
) {

    var enabledButton by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            variants.forEach { text ->
                var checkSuccess by remember { mutableStateOf(false) }
                var checkError by remember { mutableStateOf(false) }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (text == correctOption[0]) {
                            checkSuccess = true
                            onSuccess(true)
                        } else {
                            checkError = true
                            onError(true)
                        }
                        onTextChange(text)
                        enabledButton = false
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

@Composable
private fun BottomCard(
    success: Boolean,
    correctOption: Array<String>,
    translationWord: String,
    remark: String,
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
                modifier = Modifier.fillMaxWidth(),
                text = correctOption[0],
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
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
private fun SubLessonNewInfoScreenPreview() {
    SubLessonListenAndChooseItem(
        SubLessonModel(
            1,
            completed = mutableStateOf(false),
            type = SubLessonsType.SubLessonChoosingOption
        ),
        onCompleted = {}
    )
}