package com.example.peil.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    var isLoading by mutableStateOf(true)

    init {
        synchronizeLessons()
    }

    private fun synchronizeLessons() = viewModelScope.launch {
        isLoading = true
        isLoading = when(repository.synchronizeLessons()) {
            is NetworkResult.Error -> {
                false
            }

            is NetworkResult.Success -> {
                false
            }
        }
    }

}