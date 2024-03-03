package com.example.peil.ui.screens.sublessons.hint

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tungsten
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.screens.learning_lesson.SubLessonsType
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.HintYellow
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.LoginButton
import com.example.peil.ui.view_components.text.HeaderLessonText

@Composable
fun SubLessonHintItem(
    subLessonItem: SubLessonModel,
    onCompleted: (completed: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .size(30.dp)
                .graphicsLayer(rotationZ = 180F),
            imageVector = Icons.Default.Tungsten,
            contentDescription = null,
            tint = HintYellow
        )
        HeaderLessonText(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            headerText = subLessonItem.header
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = subLessonItem.newWord,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp
        )
        InfoBlock(elementsList = subLessonItem.correctOption.toList())
        Spacer(modifier = Modifier.weight(1f))
        LoginButton(
            modifier = Modifier.padding(16.dp),
            textButton = R.string.continue_text,
            horizontalPadding = 0.dp,
            onClick = {
                onCompleted(true)
            },
            content = {}
        )
    }
}

@Composable
private fun InfoBlock(elementsList: List<String>) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(2.dp, GreyLightBD, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        content = {
            items(elementsList) { element ->
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = element,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Divider (
                    color = GreyLightBD,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                )
            }
        }
    )
}

@Preview
@Composable
private fun SubLessonHintItemPreview() {
    SubLessonHintItem(SubLessonModel(idSubLesson = 0, type = SubLessonsType.SubLessonHintItem), onCompleted = {})
}