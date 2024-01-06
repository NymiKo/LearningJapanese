package com.example.peil.ui.screens.learning_lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import javax.inject.Inject

class LearningLessonViewModel @Inject constructor(): ViewModel() {

    private val _subLessons = listOf(
            SubLessonModel(
            idSubLesson = 0,
            header = "Смотри-ка, кое-что новенькое!",
            newWord = "Konnichiwa / こんにちは",
            translationWord = "Здравствуйте",
            completed = mutableStateOf(false),
            type = 0
        ),
            SubLessonModel(
                idSubLesson = 1,
                header = "Выбери слово, которое переводится как: \"Привет\".",
                newWord = "Konnichiwa / こんにちは",
                translationWord = "Здравствуйте",
                completed = mutableStateOf(false),
                options = arrayOf("Konnichiwa", "Arigatou"),
                correctOption = "Konnichiwa",
                type = 1
            )
        ).toMutableStateList()

    val subLessons: List<SubLessonModel> get() = _subLessons

    var progress by mutableFloatStateOf(0.0F)

    fun updateCompleted(item: SubLessonModel, completed: Boolean) {
        _subLessons.find { it.idSubLesson == item.idSubLesson }?.let { it.completed.value = completed }
        val completedSubLessons = _subLessons.filter { it.completed.value }
        progress = completedSubLessons.size.toFloat() / _subLessons.size.toFloat()
    }


}