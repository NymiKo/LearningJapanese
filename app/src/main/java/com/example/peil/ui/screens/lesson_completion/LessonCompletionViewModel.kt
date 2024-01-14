package com.example.peil.ui.screens.lesson_completion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.lesson_completion.data.LessonCompletionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonCompletionViewModel @Inject constructor(
    private val repository: LessonCompletionRepository
): ViewModel() {

    var loading by mutableStateOf(false)
    var success by mutableStateOf(false)

    fun lessonCompleted(idLesson: Int) = viewModelScope.launch {
        loading = true
        when(repository.lessonCompleted(idLesson)) {
            is NetworkResult.Error -> {
                loading = false
            }
            is NetworkResult.Success -> {
                success = true
            }
        }
    }
}