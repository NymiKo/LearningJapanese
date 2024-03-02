package com.example.peil.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.profile.data.model.ProfileModel
import com.example.peil.ui.screens.profile.updateProfileArg
import com.example.peil.ui.screens.settings.data.SettingsRepository
import com.example.peil.ui.screens.settings.navigation.updateSettingsArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: SettingsRepository
): ViewModel() {

    private val _profile = MutableLiveData<ProfileModel>()
    val profile: LiveData<ProfileModel> = _profile

    init {
        getFullProfile()
    }

    fun getFullProfile() = viewModelScope.launch {
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

    fun clearAllLessons() = viewModelScope.launch {
        repository.clearAllTablesInLocalDB()
    }
}