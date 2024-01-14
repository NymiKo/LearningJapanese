package com.example.peil.ui.screens.lesson_completion.data

import com.example.peil.data.NetworkResult

interface LessonCompletionRepository {
    suspend fun lessonCompleted(idLesson: Int): NetworkResult<Unit>
}