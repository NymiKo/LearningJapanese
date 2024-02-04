package com.example.peil.main.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("synchronizeLessons.php")
    suspend fun synchronizeLessons(@Query("lessons[]") lessons: List<Int>): Response<Unit>
}