package com.ravitej.dependenciesapp.splash

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.ravitej.dependenciesapp.splash.SplashFragment.Companion.USER_NAME
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SplashViewModel @AssistedInject constructor(
    @Assisted arguments: Bundle
) : ViewModel() {

    private val _userName: MutableStateFlow<String> = MutableStateFlow(arguments.getString(USER_NAME, ""))
    val userName: StateFlow<String> = _userName

}
