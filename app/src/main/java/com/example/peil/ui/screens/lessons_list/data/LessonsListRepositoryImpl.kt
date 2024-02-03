package com.example.peil.ui.screens.lessons_list.data

import com.example.peil.data.NetworkResult
import com.example.peil.data.audio_loader.AudioLoader
import com.example.peil.data.handleApi
import com.example.peil.data.image_loader.ImageLoader
import com.example.peil.data.room.dao.LessonDao
import com.example.peil.data.room.dao.SubLessonDao
import com.example.peil.data.room.entities.LessonEntity
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonService
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LessonsListRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val lessonsListService: LessonsListService,
    private val lessonDao: LessonDao,
    private val subLessonDao: SubLessonDao,
    private val learningLessonService: LearningLessonService,
    private val imageLoader: ImageLoader,
    private val audioLoader: AudioLoader
) : LessonsListRepository {
    override suspend fun getLessonsList(): NetworkResult<Map<Int, List<LessonModel>>> =
        withContext(ioDispatcher) {
            return@withContext when (val result =
                handleApi { lessonsListService.getLessonsList() }) {
                is NetworkResult.Error -> {
                    if (result.code == 105) {
                        NetworkResult.Success(lessonDao.getLessonsList().map { it.toLessonModel() }
                            .groupBy { it.chapter }.toSortedMap())
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

    override suspend fun insertLessonInLocalStorage(lesson: LessonModel): Boolean =
        withContext(ioDispatcher) {
            insertSubLessonInLocalStorage(lesson.idLesson)
            async {
                val lessonImagePath = imageLoader.downloadAndSaveImage(
                    lesson.image,
                    CHILD_LESSONS,
                    "lessons_${System.currentTimeMillis()}.webp"
                )
                val localLesson = lesson.copy(image = lessonImagePath)
                lessonDao.insertLesson(LessonEntity.toLessonEntity(localLesson))
            }.await() > 0
        }

    override suspend fun insertSubLessonInLocalStorage(idLesson: Int): Unit = withContext(ioDispatcher) {
        when (val result = handleApi { learningLessonService.getSubLesson(idLesson) }) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                result.data.map {
                    async {
                        val subLessonImagePath = imageLoader.downloadAndSaveImage(
                            it.lessonImage,
                            CHILD_SUBLESSONS,
                            "sublessons_${System.currentTimeMillis()}.webp"
                        )
                        val subLessonAudioPath = audioLoader.downloadAndSaveAudio(it.audio, "sublessons_${System.currentTimeMillis()}.mp3")
                        subLessonDao.insertSubLesson(it.copy(lessonImage = subLessonImagePath, audio = subLessonAudioPath).toSubLessonEntity())
                    }.await()
                }
            }
        }
    }


    companion object {
        const val CHILD_LESSONS = "lessons"
        const val CHILD_SUBLESSONS = "sublessons"
    }
}