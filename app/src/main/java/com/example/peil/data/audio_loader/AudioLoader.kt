package com.example.peil.data.audio_loader

interface AudioLoader {
    suspend fun downloadAndSaveAudio(audioUrl: String, fileName: String): String
}