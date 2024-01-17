package com.example.peil.ui.screens.learning_lesson.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.peil.R
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.baseBlue

@Composable
fun CancelLessonDialog(
    modifier: Modifier = Modifier,
    onLessonsListScreen: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest::invoke) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.want_to_get_out),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.progress_not_be_saved),
                fontSize = 14.sp,
                color = GreyLightBD,
                textAlign = TextAlign.Center
            )
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onDismissRequest::invoke
            ) {
                Text(
                    text = stringResource(id = R.string.continue_studies).uppercase(),
                    fontSize = 14.sp,
                    color = baseBlue
                )
            }
            TextButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onLessonsListScreen::invoke
            ) {
                Text(
                    text = stringResource(id = R.string.exit).uppercase(),
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview
@Composable
private fun CancelLessonDialogPreview() {
    CancelLessonDialog(onLessonsListScreen = {}, onDismissRequest = {})
}