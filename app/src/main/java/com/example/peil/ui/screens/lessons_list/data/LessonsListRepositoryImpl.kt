package com.example.peil.ui.screens.lessons_list.data

import android.util.Log
import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonsListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val lessonsListService: LessonsListService
) : LessonsListRepository {
    override suspend fun getLessonsList(): NetworkResult<Map<Int, List<LessonModel>>> =
        withContext(ioDispatcher) {
            return@withContext when (val result =
                handleApi { lessonsListService.getLessonsList() }) {
                is NetworkResult.Error -> {
                    NetworkResult.Error(result.code)
                }

                is NetworkResult.Success -> {
                    NetworkResult.Success(result.data.lessonsList.map {
                        LessonModel(
                            it.idLesson,
                            it.header,
                            it.image,
                            it.chapter,
                            result.data.lessonsCompletedList.contains(it.idLesson)
                        )
                    }.groupBy { it.chapter }.toSortedMap())
                }
            }
        }
}