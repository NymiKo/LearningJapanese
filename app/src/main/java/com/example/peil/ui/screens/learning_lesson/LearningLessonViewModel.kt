package com.example.peil.ui.screens.learning_lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.learning_lesson.data.LearningLessonRepository
import com.example.peil.ui.screens.learning_lesson.data.model.SubLessonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearningLessonViewModel @Inject constructor(
    private val repository: LearningLessonRepository
) : ViewModel() {

    private val _subLessons = listOf<SubLessonModel>().toMutableStateList()
    val subLessons: List<SubLessonModel> get() = _subLessons

    var progress by mutableFloatStateOf(0.0F)

    fun getSubLessonsList(idLesson: Int) = viewModelScope.launch {
        when(val result = repository.getSubLessons(idLesson)) {
            is NetworkResult.Error -> {

            }
            is NetworkResult.Success -> {
                _subLessons.clear()
                _subLessons.addAll(result.data)
            }
        }
    }

    fun updateCompleted(item: SubLessonModel, completed: Boolean) {
        if (completed) {
            _subLessons.find { it.idSubLesson == item.idSubLesson }
                ?.let { it.completed.value = true }
            val completedSubLessons = _subLessons.filter { it.completed.value }
            progress = completedSubLessons.size.toFloat() / _subLessons.size.toFloat()
        } else {
            _subLessons.add(item)
        }
    }

//    SubLessonModel(
//    idSubLesson = 0,
//    header = "Смотри-ка, кое-что новенькое!",
//    lessonImage = "https://i.ytimg.com/vi/1o8hcvOBNAY/maxresdefault.jpg",
//    newWord = "Konnichiwa / こんにちは",
//    translationWord = "Здравствуйте",
//    completed = mutableStateOf(false),
//    type = 0
//    ),
//    SubLessonModel(
//    idSubLesson = 7,
//    header = "Представление себя",
//    newWord = "\"Hajimemashite\" буквально означает \"(мы встречаемся) впервые\" и  произносится при встрече.\n\n\"Douzo yoroshiku\" буквально означает \"пожалуйста, будь со мной вежлив\", поэтому эту фразу произносят в конце представления.",
//    completed = mutableStateOf(false),
//    correctOption = arrayOf("В начале", "Hajimemashite", "В конце", "Douzo yoroshiku"),
//    type = 5
//    ),
//    SubLessonModel(
//    idSubLesson = 6,
//    header = "Расставь слова в правильном порядке.",
//    translationWord = "Я - Акира",
//    completed = mutableStateOf(false),
//    options = arrayOf("Watashi", "desu.", "wa", "Akira"),
//    correctOption = arrayOf("Watashi", "wa", "Akira", "desu."),
//    type = 4
//    ),
//    SubLessonModel(
//    idSubLesson = 1,
//    header = "Выбери слово, которое переводится как: \"Привет\".",
//    translationWord = "Здравствуйте",
//    completed = mutableStateOf(false),
//    options = arrayOf("Konnichiwa", "Arigatou"),
//    correctOption = arrayOf("Konnichiwa"),
//    type = 1
//    ),
//    SubLessonModel(
//    idSubLesson = 2,
//    header = "Смотри-ка, кое-что новенькое!",
//    lessonImage = "https://tipsparatuviaje.com/wp-content/uploads/2018/10/no-des-apreton-de-manos.jpg",
//    newWord = "Hajimemashite / はじめまして",
//    translationWord = "Приятно познакомиться",
//    completed = mutableStateOf(false),
//    type = 0
//    ),
//    SubLessonModel(
//    idSubLesson = 3,
//    header = "Верно или неверно?",
//    newWord = "\"Hajimemashite\" означает \"приятно познакомиться\".",
//    translationWord = "",
//    completed = mutableStateOf(false),
//    options = arrayOf("Правильно", "Неправильно"),
//    correctOption = arrayOf("Правильно"),
//    remark = "\"Hajimemashite\" говорят, когда встречают кого-то впервые",
//    type = 2
//    ),
//    SubLessonModel(
//    idSubLesson = 4,
//    header = "Выбери предложение, чтобы сказать: \"Приятно познакомиться\".",
//    translationWord = "Приятно познакомиться",
//    completed = mutableStateOf(false),
//    options = arrayOf("Konnichiwa", "Hajimemashite"),
//    correctOption = arrayOf("Hajimemashite"),
//    type = 1
//    ),
//    SubLessonModel(
//    idSubLesson = 5,
//    header = "Закончи предложения.",
//    translationWord = "Здравствуйте. Приятно познакомиться.",
//    sentence = arrayOf("Konnichiwa. ", "", "jime", "", "shite."),
//    completed = mutableStateOf(false),
//    options = arrayOf("ma", "Ha", "na", "Ta"),
//    correctOption = arrayOf("Ha", "ma"),
//    remark = "Konnichiwa. Hajimemashite.",
//    type = 3
//    )
}