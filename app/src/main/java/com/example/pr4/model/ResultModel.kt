package com.example.pr4.models

data class ResultModel(
    val isGameWon: Boolean = false,
    val secretWord: String = "",
    val attempts: Int = 0
)