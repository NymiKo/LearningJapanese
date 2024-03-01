package com.example.peil.main

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.data.room.dao.LessonDao
import com.example.peil.main.data.MainService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val mainService: MainService,
    private val lessonDao: LessonDao
): MainRepository {
    override suspend fun synchronizeLessons(): NetworkResult<Boolean> = withContext(ioDispatcher) {
//        val lessons = lessonDao.getIdUnsychronizedLessonsList()
//        if (lessons.isNotEmpty()) {
//            return@withContext when(val result = handleApi { mainService.synchronizeLessons(lessons) }) {
//                is NetworkResult.Error -> {
//                    NetworkResult.Error(result.code)
//                }
//                is NetworkResult.Success -> {
//                    lessonDao.updateSynchronizedLessons(lessons)
//                    NetworkResult.Success(true)
//                }
//            }
//        } else {
//            return@withContext NetworkResult.Success(true)
//        }
        return@withContext NetworkResult.Success(true)
    }
}