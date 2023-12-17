package com.example.peil.ui.view_components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(modifier: Modifier = Modifier, title: Int? = null, imageVector: ImageVector, navigationClick: () -> Unit) = TopAppBar(
    title = { if (title != null)
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    },
    navigationIcon = {
        IconButton(
            onClick = { navigationClick() }
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        }
    }
)