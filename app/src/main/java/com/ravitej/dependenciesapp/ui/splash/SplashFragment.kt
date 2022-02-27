package com.ravitej.dependenciesapp.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ravitej.dependenciesapp.R
import com.ravitej.dependenciesapp.databinding.FragmentSplashBinding
import com.ravitej.dependenciesapp.util.Error
import com.ravitej.dependenciesapp.util.Loading
import com.ravitej.dependenciesapp.util.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var splashViewModelFactory: SplashViewModelFactory
    private lateinit var binding: FragmentSplashBinding
    private val adapter: CharacterListAdapter by lazy { CharacterListAdapter() }

    private val viewModel: SplashViewModel by viewModels {
        provideFactory(
            splashViewModelFactory,
            Bundle().apply { putString(USER_NAME, "Ravi Tej") }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        initAdapters()
        initObservers()

        return binding.root
    }

    private fun initAdapters() {
        binding.characterRV.adapter = adapter
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.testData
                .collect {
                    when (it.status) {
                        is Success -> {
                            adapter.submitList(it.status.data)
                        }
                        is Error -> {
                            Log.e("Check", it.status.message)
                        }
                        is Loading -> {
                            Log.e("Check", "Loading")
                        }
                    }
                }
        }
    }

    companion object {
        const val USER_NAME = "user_name"
    }
}
