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
            lessonImage = "https://i.ytimg.com/vi/1o8hcvOBNAY/maxresdefault.jpg",
            newWord = "Konnichiwa / こんにちは",
            translationWord = "Здравствуйте",
            completed = mutableStateOf(false),
            type = 0
        ),
        SubLessonModel(
            idSubLesson = 1,
            header = "Выбери слово, которое переводится как: \"Привет\".",
            newWord = "",
            translationWord = "Здравствуйте",
            completed = mutableStateOf(false),
            options = arrayOf("Konnichiwa", "Arigatou"),
            correctOption = arrayOf("Konnichiwa"),
            type = 1
        ),
        SubLessonModel(
            idSubLesson = 2,
            header = "Смотри-ка, кое-что новенькое!",
            lessonImage = "https://tipsparatuviaje.com/wp-content/uploads/2018/10/no-des-apreton-de-manos.jpg",
            newWord = "Hajimemashite / はじめまして",
            translationWord = "Приятно познакомиться",
            completed = mutableStateOf(false),
            type = 0
        ),
        SubLessonModel(
            idSubLesson = 3,
            header = "Верно или неверно?",
            newWord = "\"Hajimemashite\" означает \"приятно познакомиться\".",
            translationWord = "",
            completed = mutableStateOf(false),
            options = arrayOf("Правильно", "Неправильно"),
            correctOption = arrayOf("Правильно"),
            remark = "\"Hajimemashite\" говорят, когда встречают кого-то впервые",
            type = 2
        ),
        SubLessonModel(
            idSubLesson = 4,
            header = "Выбери предложение, чтобы сказать: \"Приятно познакомиться\".",
            newWord = "",
            translationWord = "Приятно познакомиться",
            completed = mutableStateOf(false),
            options = arrayOf("Konnichiwa", "Hajimemashite"),
            correctOption = arrayOf("Hajimemashite"),
            type = 1
        ),
        SubLessonModel(
            idSubLesson = 5,
            header = "Закончи предложения.",
            newWord = "",
            translationWord = "",
            sentence = arrayOf("Konnichiwa. ", "", "jime", "","shite."),
            completed = mutableStateOf(false),
            options = arrayOf("ma", "Ha", "na", "Ta"),
            correctOption = arrayOf("Ha", "ma"),
            type = 3
        )
    ).toMutableStateList()

    val subLessons: List<SubLessonModel> get() = _subLessons

    var progress by mutableFloatStateOf(0.0F)

    fun updateCompleted(item: SubLessonModel, completed: Boolean) {
        if (completed) {
            _subLessons.find { it.idSubLesson == item.idSubLesson }?.let { it.completed.value = completed }
            val completedSubLessons = _subLessons.filter { it.completed.value }
            progress = completedSubLessons.size.toFloat() / _subLessons.size.toFloat()
        } else {
            _subLessons.add(item)
        }
    }
}