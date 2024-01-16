package com.example.peil.ui.view_components.icon

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.peil.ui.theme.baseBlue

@Composable
fun IconVolume(modifier: Modifier = Modifier, audio: String, size: Dp = 25.dp) {
    val mediaPlayer = MediaPlayer()
    mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
    mediaPlayer.setDataSource(audio)
    mediaPlayer.prepare()

    Icon(
        modifier = Modifier.padding(start = 8.dp).size(size).clickable { mediaPlayer.start() },
        imageVector = Icons.Default.VolumeUp,
        contentDescription = null,
        tint = baseBlue
    )
}