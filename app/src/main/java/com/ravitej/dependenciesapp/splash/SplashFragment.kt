package com.ravitej.dependenciesapp.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ravitej.dependenciesapp.R
import com.ravitej.dependenciesapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory
    private lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by viewModels {
        provideFactory(
            splashViewModelFactory,
            Bundle().apply { putString(USER_NAME, "Ravi Tej") }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.userName
                .collect { binding.userName.text = "Hello $it" }
        }
    }

    companion object {
        const val USER_NAME = "user_name"
    }
}
