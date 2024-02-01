package com.example.peil.ui.screens.lessons_list.state

import com.example.peil.R
import com.example.peil.ui.screens.lessons_list.data.model.LessonCategory

data class LessonListScreenState(
    val progressLoading: Boolean = true,
    val progressStudy: Float = 0.0F,
    val lessonsList: List<LessonCategory> = emptyList(),
    val isError: Boolean,
    val errorMessage: Int = R.string.unknown_error
)
