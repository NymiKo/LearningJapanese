package com.example.peil.ui.screens.lesson_completion.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LessonCompletionService {
    @GET("lessonCompleted.php")
    suspend fun lessonCompleted(@Query("idLesson") idLesson: Int): Response<Unit>
}