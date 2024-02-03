package com.example.peil.data.audio_loader

import android.content.Context
import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioLoaderImpl @Inject constructor(
    private val context: Context,
    private val learningLessonService: LearningLessonService
) : AudioLoader {

    override suspend fun downloadAndSaveAudio(audioUrl: String, fileName: String): String =
        withContext(Dispatchers.IO) {
            try {
                when (val result = handleApi { learningLessonService.downloadAudio(audioUrl) }) {
                    is NetworkResult.Error -> {
                        ""
                    }

                    is NetworkResult.Success -> {
                        val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
                        outputStream.write(result.data.bytes())
                        outputStream.close()
                        context.getFileStreamPath(fileName).absolutePath
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }

    companion object {
        private const val CHILD_AUDIO = "audio"
    }
}