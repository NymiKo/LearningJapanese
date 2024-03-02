package com.example.peil.ui.screens.lesson_completion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peil.R
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.LoginButton

@Composable
fun LessonCompletionScreen(
    viewModel: LessonCompletionViewModel,
    idLesson: Int,
    onLessonListScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (viewModel.loading) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp), color = baseBlue)
                } else {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(id = R.drawable.lesson_completion),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.lesson_completed),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        LoginButton(
            textButton = R.string.continue_text,
            enabled = !viewModel.loading,
            onClick = {
                viewModel.lessonCompleted(idLesson = idLesson)
                onLessonListScreen()
            },
            content = {}
        )
    }
}

@Preview
@Composable
private fun LessonCompletionScreenPreview() {
    LessonCompletionScreen(hiltViewModel(), 0, {})
}