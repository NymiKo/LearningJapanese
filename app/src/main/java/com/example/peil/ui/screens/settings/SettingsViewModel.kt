package com.example.peil.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.profile.data.model.ProfileModel
import com.example.peil.ui.screens.settings.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
): ViewModel() {

    private val _profile = MutableLiveData<ProfileModel>()
    val profile: LiveData<ProfileModel> = _profile

    init {
        getFullProfile()
    }

    private fun getFullProfile() = viewModelScope.launch {
        when(val result = repository.getFullProfile()) {
            is NetworkResult.Error -> {

            }
            is NetworkResult.Success -> {
                _profile.value = result.data
            }
        }
    }

    fun loadAvatar(file: File) = viewModelScope.launch {
        repository.loadAvatar(file)
    }
}