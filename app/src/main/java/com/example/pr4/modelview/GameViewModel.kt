package com.example.pr4.viewmodels

import androidx.lifecycle.ViewModel
import com.example.pr4.models.Difficulty
import com.example.pr4.models.GameModel
import com.example.pr4.repository.MockWordRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(private val wordRepository: MockWordRepository) : ViewModel() {
    private val _gameState = MutableStateFlow(GameModel())
    val gameState = _gameState.asStateFlow()

    fun startNewGame(difficulty: Difficulty) {
        val secretWord = wordRepository.getWordByDifficulty(difficulty).uppercase()
        _gameState.value = GameModel(
            secretWord = secretWord,
            revealedWord = "*".repeat(secretWord.length),
            difficulty = difficulty
        )
    }

    fun guessLetter(letter: Char) {
        val currentState = _gameState.value
        val upperLetter = letter.uppercaseChar() // Convertir letra a mayúscula
        if (currentState.usedLetters.contains(upperLetter)) return

        val updatedUsedLetters = currentState.usedLetters + upperLetter
        var updatedRevealedWord = currentState.revealedWord
        var updatedAttempts = currentState.attempts
        var isGameWon = false
        var isGameLost = false

        if (currentState.secretWord.contains(upperLetter, ignoreCase = true)) {
            updatedRevealedWord = currentState.secretWord
                .mapIndexed { index, char ->
                    if (char.equals(upperLetter, ignoreCase = true)) upperLetter
                    else currentState.revealedWord[index]
                }
                .joinToString("")
            isGameWon = updatedRevealedWord == currentState.secretWord
        } else {
            updatedAttempts++
            isGameLost = updatedAttempts >= currentState.maxAttempts
        }

        _gameState.update { state ->
            state.copy(
                revealedWord = updatedRevealedWord,
                attempts = updatedAttempts,
                usedLetters = updatedUsedLetters,
                isGameWon = isGameWon,
                isGameLost = isGameLost
            )
        }
    }
}