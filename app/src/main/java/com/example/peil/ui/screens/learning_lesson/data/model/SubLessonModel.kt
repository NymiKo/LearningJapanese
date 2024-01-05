package com.example.peil.ui.screens.learning_lesson.data.model

data class SubLessonModel(
    val idSubLesson: Int,
    val header: String = "",
    val newWord: String = "",
    val translationWord: String = "",
    val completed: Boolean,
    val variants: Array<String> = arrayOf(""),
    val type: Int
)
