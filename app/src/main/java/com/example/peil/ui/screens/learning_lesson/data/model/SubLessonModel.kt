package com.example.peil.ui.screens.learning_lesson.data.model

data class SubLessonModel(
    val idSubLesson: Int,
    val header: String = "",
    val newWord: String = "",
    val translationWord: String = "",
    val completed: Boolean,
    val options: Array<String> = arrayOf(""),
    val correctOption: String = "",
    val type: Int
)
