package com.ravitej.dependenciesapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SplashViewModelFactory {
    fun create(arguments: Bundle): SplashViewModel
}

fun provideFactory(
    assistedFactory: SplashViewModelFactory,
    arguments: Bundle
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(arguments) as T
    }
}
