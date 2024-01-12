package com.example.peil.ui.screens.learning_lesson.data

import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel

interface LearningLessonRepository {
    suspend fun getSubLessons(idLesson: Int): NetworkResult<List<SubLessonModel>>
}