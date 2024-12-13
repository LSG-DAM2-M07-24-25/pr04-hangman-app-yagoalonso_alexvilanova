package com.example.pr4.modelview

import androidx.lifecycle.ViewModel
import com.example.pr4.models.ResultModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultViewModel : ViewModel() {

    private val _resultState = MutableStateFlow(ResultModel())


    val resultState: StateFlow<ResultModel> get() = _resultState


    fun updateResultState(isGameWon: Boolean, secretWord: String, attempts: Int) {
        _resultState.value = _resultState.value.copy(
            isGameWon = isGameWon,
            secretWord = secretWord,
            attempts = attempts
        )
    }
}