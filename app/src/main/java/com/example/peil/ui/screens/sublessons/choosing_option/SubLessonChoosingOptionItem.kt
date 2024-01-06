package com.example.peil.ui.screens.sublessons.choosing_option

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.Green
import com.example.peil.ui.theme.GreenLight
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.RedLight
import com.example.peil.ui.view_components.LoginButton

@Composable
fun SubLessonChoosingOptionItem(subLessonItem: SubLessonModel) {

    var textSelectedOption by remember { mutableStateOf("             ") }
    var success by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLesson(headerText = subLessonItem.header)
        NewWordLessonField(text = textSelectedOption, success = success, error = error)
        OptionButtons(
            variants = subLessonItem.options,
            correctOption = subLessonItem.correctOption,
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
                translationWord = subLessonItem.translationWord
            )
        }
    }
}

@Composable
private fun HeaderLesson(modifier: Modifier = Modifier, headerText: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = headerText,
        fontWeight = FontWeight.Bold,
        color = Blue,
        fontSize = 14.sp
    )
}

@Composable
private fun NewWordLessonField(
    modifier: Modifier = Modifier,
    text: String,
    error: Boolean,
    success: Boolean
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        fontSize = 18.sp,
        color = if (success) Green else if (error) Color.Red else Blue
    )
}

@Composable
private fun OptionButtons(
    modifier: Modifier = Modifier,
    variants: Array<String>,
    correctOption: String,
    onTextChange: (text: String) -> Unit,
    onSuccess: (success: Boolean) -> Unit,
    onError: (error: Boolean) -> Unit
) {

    var enabledButton by remember { mutableStateOf(true) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(variants) { text ->
                var checkSuccess by remember { mutableStateOf(false) }
                var checkError by remember { mutableStateOf(false) }

                Button(
                    onClick = {
                        if (text == correctOption) {
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
                        containerColor = if (checkSuccess) Green else if (checkError) Color.Red else MaterialTheme.colorScheme.primary,
                        disabledContainerColor = if (checkSuccess) Green else if (checkError) Color.Red else MaterialTheme.colorScheme.primary
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
        })
}

@Composable
private fun BottomCard(success: Boolean, correctOption: String, translationWord: String) {
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
            Text(text = stringResource(id = R.string.answer), fontSize = 12.sp, color = GreyLightBD)
            Text(
                text = correctOption,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            Text(text = translationWord, fontSize = 14.sp, color = GreyLight)
            LoginButton(
                textButton = R.string.continue_text,
                containerColor = Green,
                horizontalPadding = 0.dp,
                onClick = { }) {}
        }
    }
}

@Composable
private fun IconWithText(success: Boolean) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (success) GreenLight else RedLight, shape = CircleShape)
                .padding(3.dp),
            imageVector = if (success) Icons.Default.Check else Icons.Default.Close,
            contentDescription = "",
            tint = if (success) Green else Color.Red
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = if (success) R.string.correctly else R.string.wrong),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (success) Green else Color.Red
        )
    }
}

@Preview
@Composable
private fun SubLessonNewInfoScreenPreview() {
    SubLessonChoosingOptionItem(SubLessonModel(1, completed = false, type = 1))
}