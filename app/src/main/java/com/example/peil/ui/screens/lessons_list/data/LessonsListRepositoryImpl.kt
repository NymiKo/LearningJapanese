package com.example.peil.ui.screens.lessons_list.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.handleApi
import com.example.peil.data.image_loader.ImageLoader
import com.example.peil.data.room.dao.LessonDao
import com.example.peil.data.room.entities.LessonEntity
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonsListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val lessonsListService: LessonsListService,
    private val lessonDao: LessonDao,
    private val imageLoader: ImageLoader
) : LessonsListRepository {
    override suspend fun getLessonsList(): NetworkResult<Map<Int, List<LessonModel>>> =
        withContext(ioDispatcher) {
            return@withContext when (val result =
                handleApi { lessonsListService.getLessonsList() }) {
                is NetworkResult.Error -> {
                    if (result.code == 105) {
                        NetworkResult.Success(lessonDao.getLessonsList().map { it.toLessonModel() }.groupBy { it.chapter }.toSortedMap())
                    } else {
                        NetworkResult.Error(result.code)
                    }
                }

                is NetworkResult.Success -> {
                    val lessonsListFromLocalStorage = lessonDao.getIdLessonsList()
                    NetworkResult.Success(result.data.lessonsList.map {
                        LessonModel(
                            it.idLesson,
                            it.header,
                            it.image,
                            it.chapter,
                            result.data.lessonsCompletedList.contains(it.idLesson),
                            lessonsListFromLocalStorage.contains(it.idLesson)
                        )
                    }.groupBy { it.chapter }.toSortedMap())
                }
            }
        }

    override suspend fun insertLessonInLocalStorage(lesson: LessonModel): Boolean = withContext(ioDispatcher) {
        async {
            val lessonImagePath = imageLoader.downloadAndSaveImage(lesson.image, CHILD_LESSONS, "lessons_${System.currentTimeMillis()}.webp")
            val localLesson = lesson.copy(image = lessonImagePath)
            lessonDao.insertLesson(LessonEntity.toLessonEntity(localLesson))
        }.await() > 0
    }

    companion object {
        const val CHILD_LESSONS = "lessons"
        const val CHILD_SUBLESSONS = "sublessons"
    }
}