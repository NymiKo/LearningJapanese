package com.example.peil.ui.screens.sublessons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import com.example.peil.ui.theme.Blue

@Composable
fun SubLessonChoosingOptionItem(subLessonItem: SubLessonModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderLesson(headerText = subLessonItem.header)
        NewWordLessonField()
        OptionButtons(variants = subLessonItem.variants)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewWordLessonField(modifier: Modifier = Modifier) {
    TextField(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .defaultMinSize(minWidth = 40.dp)
            .height(20.dp),
        value = "",
        onValueChange = {},
        readOnly = true,
        singleLine = true,
        textStyle = TextStyle.Default.copy(fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Blue,
            focusedIndicatorColor = Blue
        )
    )
}

@Composable
private fun OptionButtons(modifier: Modifier = Modifier, variants: Array<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
        items(variants) { text ->
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
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

@Preview
@Composable
private fun SubLessonNewInfoScreenPreview() {
    SubLessonChoosingOptionItem(SubLessonModel(1, completed = false, type = 1))
}