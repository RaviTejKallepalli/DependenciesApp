package com.ravitej.dependenciesapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ravitej.dependenciesapp.R
import com.ravitej.dependenciesapp.databinding.FragmentServiceBinding
import com.ravitej.dependenciesapp.service.MediaPlayerService

class ServiceFragment : Fragment() {

    private lateinit var binding: FragmentServiceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service, container, false)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        binding.startButton.setOnClickListener {
            // Start the service
            Intent(requireActivity(), MediaPlayerService::class.java).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    requireActivity().startForegroundService(this)
                } else {
                    requireActivity().startService(this)
                }
            }
        }

        binding.stopButton.setOnClickListener {
            // Stop the service
        }
    }
}
