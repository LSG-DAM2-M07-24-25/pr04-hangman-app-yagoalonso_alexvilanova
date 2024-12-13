package com.example.pr4.models


data class GameModel(
    val secretWord: String = "",
    val revealedWord: String = "",
    val attempts: Int = 0,
    val maxAttempts: Int = 7,
    val usedLetters: Set<Char> = emptySet(),
    val isGameWon: Boolean = false,
    val isGameLost: Boolean = false,
    val difficulty: Difficulty = Difficulty.NORMAL
)
