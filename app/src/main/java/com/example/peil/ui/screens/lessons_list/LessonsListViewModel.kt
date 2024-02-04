package com.example.peil.ui.screens.lessons_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepository
import com.example.peil.ui.screens.lessons_list.data.model.LessonCategory
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import com.example.peil.ui.screens.lessons_list.state.LessonListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsListViewModel @Inject constructor(
    private val repository: LessonsListRepository
) : ViewModel() {

    private val _state = mutableStateOf(LessonListScreenState(isError = false))
    val state: State<LessonListScreenState> = _state

    init {
        getLessonsList()
    }

    private fun getLessonsList() = viewModelScope.launch {
        _state.value = state.value.copy(progressLoading = true)
        when (val result = repository.getLessonsList()) {
            is NetworkResult.Success -> {
                val completedSubLessons =
                    result.data.values.sumOf { list -> list.filter { it.completed }.size }
                val listSize = result.data.values.sumOf { it.size }
                val progress = completedSubLessons.toFloat() / listSize.toFloat()

                _state.value = state.value.copy(
                    progressLoading = false,
                    lessonsList = result.data.map { LessonCategory(it.key, it.value) },
                    progressStudy = progress
                )
            }

            is NetworkResult.Error -> {
                _state.value = state.value.copy(isError = true, progressLoading = false)
            }
        }
    }

    fun saveLesson(lesson: LessonModel) = viewModelScope.launch {
        _state.value = state.value.copy(lessonsList = state.value.lessonsList.map { lessonCategory ->
            lessonCategory.copy(lessonsList = lessonCategory.lessonsList.map {  lessonModel ->
                lessonModel.takeIf { it.idLesson != lesson.idLesson } ?: lessonModel.copy(isDownloading = true)
            })
        })
        when(repository.insertLessonInLocalStorage(lesson)) {
            true -> {
                _state.value = state.value.copy(lessonsList = state.value.lessonsList.map { lessonCategory ->
                    lessonCategory.copy(lessonsList = lessonCategory.lessonsList.map { lessonModel ->
                        lessonModel.takeIf { it.idLesson != lesson.idLesson } ?: lessonModel.copy(isDownloading = false, isUploaded = true)
                    })
                })
            }
            false -> {
                _state.value = state.value.copy(lessonsList = state.value.lessonsList.map { lessonCategory ->
                    lessonCategory.copy(lessonsList = lessonCategory.lessonsList.map {  lessonModel ->
                        lessonModel.takeIf { it.idLesson != lesson.idLesson } ?: lessonModel.copy(isDownloading = false)
                    })
                })
            }
        }
    }
}