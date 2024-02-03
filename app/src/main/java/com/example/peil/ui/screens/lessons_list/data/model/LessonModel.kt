package com.example.peil.ui.screens.lessons_list.data.model

data class LessonModel(
    val idLesson: Int,
    val header: String,
    val image: String,
    val chapter: Int,
    val completed: Boolean = false,
    val isUploaded: Boolean = true
)
