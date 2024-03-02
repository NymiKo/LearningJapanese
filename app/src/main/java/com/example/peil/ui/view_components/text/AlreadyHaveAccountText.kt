package com.example.peil.ui.view_components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.example.peil.R
import com.example.peil.ui.theme.GreyLight

@Composable
fun AlreadyHaveAccountText(modifier: Modifier = Modifier, onLoginClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = GreyLight
            )
        ) {
            append(stringResource(id = R.string.already_an_account) + " ")
        }
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = GreyLight
            )
        ) {
            append(stringResource(id = R.string.sign_in))
        }
    }
    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = { onLoginClick() },
        style = TextStyle.Default.merge(
            TextStyle(textAlign = TextAlign.Center)
        )
    )
}