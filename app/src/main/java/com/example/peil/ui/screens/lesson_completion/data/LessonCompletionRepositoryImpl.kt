package com.example.peil.ui.screens.lesson_completion.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.data.room.dao.LessonDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonCompletionRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val lessonCompletionService: LessonCompletionService,
    private val lessonDao: LessonDao
) : LessonCompletionRepository {
    override suspend fun lessonCompleted(idLesson: Int): NetworkResult<Unit> =
        withContext(ioDispatcher) {
            lessonDao.updateLesson(idLesson, true)
            return@withContext handleApi { lessonCompletionService.lessonCompleted(idLesson) }
        }
}