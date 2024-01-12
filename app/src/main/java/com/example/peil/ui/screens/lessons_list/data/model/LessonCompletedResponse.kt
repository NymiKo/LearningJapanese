package com.example.peil.ui.screens.lessons_list.data.model

data class LessonCompletedResponse(
    val lessonsCompletedList: List<Int> = emptyList(),
    val lessonsList: List<LessonResponse>
)
