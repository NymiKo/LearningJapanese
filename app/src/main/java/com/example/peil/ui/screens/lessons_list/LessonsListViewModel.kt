package com.example.peil.ui.screens.lessons_list

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.lessons_list.data.LessonsListRepository
import com.example.peil.ui.screens.lessons_list.data.model.LessonCategory
import com.example.peil.ui.screens.lessons_list.data.model.LessonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsListViewModel @Inject constructor(
    private val repository: LessonsListRepository
): ViewModel() {

    var lessonsList by mutableStateOf(listOf<LessonCategory>())

    init {
        getLessonsList()
    }

    private fun getLessonsList() = viewModelScope.launch {
        when(val result = repository.getLessonsList()) {
            is NetworkResult.Success -> {
                lessonsList = result.data.map { LessonCategory(it.key, it.value) }
            }

            is NetworkResult.Error -> {

            }
        }
    }
}