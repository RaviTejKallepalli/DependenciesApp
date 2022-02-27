package com.ravitej.dependenciesapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravitej.dependenciesapp.data.entities.CharacterDetail
import com.ravitej.dependenciesapp.data.repository.CharacterRepository
import com.ravitej.dependenciesapp.util.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel @AssistedInject constructor(
    private val repository: CharacterRepository,
    @Assisted arguments: Bundle
) : ViewModel() {

    private val _testData: MutableStateFlow<Resource<List<CharacterDetail>>> =
        MutableStateFlow(Resource.success(emptyList()))
    val testData: StateFlow<Resource<List<CharacterDetail>>> = _testData

    init {
        viewModelScope.launch {
            repository.getCharacters()
                .collect { _testData.value = it }
        }
    }
}
