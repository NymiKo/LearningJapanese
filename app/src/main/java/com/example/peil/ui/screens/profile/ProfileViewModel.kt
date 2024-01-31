package com.example.peil.ui.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peil.data.NetworkResult
import com.example.peil.ui.screens.profile.data.ProfileRepository
import com.example.peil.ui.screens.profile.data.model.ProfileModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profile = MutableLiveData<ProfileModel>()
    val profile: LiveData<ProfileModel> get() = _profile

    fun getProfile() = viewModelScope.launch {
        when (val result = repository.getProfile()) {
            is NetworkResult.Error -> {

            }

            is NetworkResult.Success -> {
                _profile.value = result.data
            }
        }
    }
}