package com.example.peil.ui.screens.learning_lesson.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.data.room.dao.SubLessonDao
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LearningLessonRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val learningLessonService: LearningLessonService,
    private val subLessonDao: SubLessonDao
): LearningLessonRepository {
    override suspend fun getSubLessons(idLesson: Int): NetworkResult<List<SubLessonModel>> = withContext(ioDispatcher) {
        val subLessonsList = subLessonDao.getSubLessons(idLesson)
        if (subLessonsList.isEmpty()) {
            return@withContext when(val result = handleApi { learningLessonService.getSubLesson(idLesson) }) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(result.code)
                }
                is NetworkResult.Success -> {
                    NetworkResult.Success(result.data.map { it.toSubLessonModel() })
                }
            }
        } else {
            NetworkResult.Success(subLessonsList.map { it.toSubLessonModel() })
        }
    }
}