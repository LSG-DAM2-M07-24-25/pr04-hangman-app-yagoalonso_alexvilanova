package com.example.pr4.modelview

import androidx.lifecycle.ViewModel
import com.example.pr4.models.GameModel
import com.example.pr4.models.MockWordRepository
import com.example.pr4.models.Difficulty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(private val wordRepository: MockWordRepository) : ViewModel() {
    private val _gameState = MutableStateFlow(GameModel())
    val gameState = _gameState.asStateFlow()

    fun startNewGame(difficulty: Difficulty) {
        val secretWord = wordRepository.getWordByDifficulty(difficulty)
        _gameState.value = GameModel(
            secretWord = secretWord.lowercase(),
            revealedWord = "*".repeat(secretWord.length),
            difficulty = difficulty
        )
    }

    fun guessLetter(letter: Char) {
        val currentState = _gameState.value
        if (currentState.usedLetters.contains(letter)) return

        val updatedUsedLetters = currentState.usedLetters + letter
        var updatedRevealedWord = currentState.revealedWord
        var updatedAttempts = currentState.attempts
        var isGameWon = false
        var isGameLost = false

        if (currentState.secretWord.contains(letter, ignoreCase = true)) {
            updatedRevealedWord = currentState.secretWord
                .mapIndexed { index, char ->
                    if (char.equals(letter, ignoreCase = true)) letter
                    else currentState.revealedWord[index]
                }
                .joinToString("")
            isGameWon = updatedRevealedWord.equals(currentState.secretWord, ignoreCase = true)
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
