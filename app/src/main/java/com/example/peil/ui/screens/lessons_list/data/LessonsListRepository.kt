package com.example.peil.ui.screens.lessons_list.data

import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel

interface LessonsListRepository {
    suspend fun getLessonsList(): NetworkResult<Map<Int, List<LessonModel>>>
    suspend fun getRemoteLessonsList(): NetworkResult<Map<Int, List<LessonModel>>>
    suspend fun insertLessonInLocalStorage(lesson: LessonModel): Boolean
    suspend fun insertSubLessonInLocalStorage(idLesson: Int)
}