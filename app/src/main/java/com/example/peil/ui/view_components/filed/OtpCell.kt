package com.example.peil.ui.view_components.filed

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.Black
import com.example.peil.ui.theme.baseBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OtpCell(
    modifier: Modifier = Modifier,
    value: String,
    isCursorVisible: Boolean = false
) {
    val scope = rememberCoroutineScope()
    val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(350)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    Box(
        modifier = modifier.border(width = if (isCursorVisible) 2.dp else 0.dp, color = baseBlue, shape = MaterialTheme.shapes.large)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = if (isCursorVisible) cursorSymbol else value,
            color = Black
        )
    }
}

