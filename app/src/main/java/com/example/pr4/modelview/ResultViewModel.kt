package com.example.pr4.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pr4.models.ResultModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ResultViewModel : ViewModel() {
    private val _resultState = MutableStateFlow(ResultModel())
    val resultState = _resultState.asStateFlow()

    fun updateResultState(isGameWon: Boolean, secretWord: String, attempts: Int) {
        _resultState.value = ResultModel(
            isGameWon = isGameWon,
            secretWord = secretWord,
            attempts = attempts
        )
    }
}
