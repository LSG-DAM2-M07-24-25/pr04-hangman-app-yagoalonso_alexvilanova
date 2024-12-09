package com.example.pr4.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pr4.models.LaunchModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LaunchViewModel : ViewModel() {
    private val _launchState = MutableStateFlow(LaunchModel())
    val launchState = _launchState.asStateFlow()
}
