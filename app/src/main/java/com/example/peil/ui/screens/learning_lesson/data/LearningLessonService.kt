package com.example.peil.ui.screens.learning_lesson.data

import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LearningLessonService {
    @GET("getSubLessons.php")
    suspend fun getSubLesson(@Query("idLesson") idLesson: Int): Response<List<SubLessonResponseModel>>
}