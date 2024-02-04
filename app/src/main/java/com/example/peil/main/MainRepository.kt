package com.example.peil.main

import com.example.peil.data.NetworkResult

interface MainRepository {
    suspend fun synchronizeLessons(): NetworkResult<Boolean>
}