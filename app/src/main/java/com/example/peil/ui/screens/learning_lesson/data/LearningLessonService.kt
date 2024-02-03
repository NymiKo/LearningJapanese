package com.example.peil.ui.screens.learning_lesson.data

import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonResponseModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface LearningLessonService {
    @GET("getSubLessons.php")
    suspend fun getSubLesson(@Query("idLesson") idLesson: Int): Response<List<SubLessonResponseModel>>

    @Streaming
    @GET
    suspend fun downloadAudio(@Url fileUrl: String): Response<ResponseBody>
}