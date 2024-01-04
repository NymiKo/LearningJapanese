package com.example.peil.ui.screens.lessons_list.data

import com.example.peil.ui.screens.lessons_list.data.model.LessonResponse
import retrofit2.Response
import retrofit2.http.GET

interface LessonsListService {
    @GET("getLessonsList.php")
    suspend fun getLessonsList(): Response<List<LessonResponse>>
}