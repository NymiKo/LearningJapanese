package com.example.peil.ui.screens.learning_lesson.data.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.peil.ui.screens.learning_lesson.SubLessonsType

data class SubLessonModel(
    val idSubLesson: Int,
    val header: String = "",
    val lessonImage: String = "",
    val newWord: String = "",
    val translationWord: String = "",
    var completed: MutableState<Boolean> = mutableStateOf(false),
    val options: Array<String> = arrayOf(""),
    val correctOption: Array<String> = arrayOf(""),
    val sentence: Array<String> = arrayOf(""),
    val remark: String = "",
    val type: SubLessonsType,
    val audio: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SubLessonModel

        if (!options.contentEquals(other.options)) return false

        return true
    }

    override fun hashCode(): Int {
        return options.contentHashCode()
    }
}
