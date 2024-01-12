package com.example.peil.ui.screens.lessons_list.data.model

data class LessonResponse(
    val idLesson: Int,
    val header: String,
    val image: String,
    val chapter: String
) {
    fun toLessonModel() = LessonModel(
        idLesson = idLesson,
        header = header,
        image = image,
        chapter = chapter
    )
}