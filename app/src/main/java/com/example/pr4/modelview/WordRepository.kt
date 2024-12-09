// File: viewmodels/WordRepository.kt
package com.example.pr4.viewmodels

import com.example.pr4.models.Difficulty
open class WordRepository {
    open fun getWordByDifficulty(difficulty: Difficulty): String {
        return when (difficulty) {
            Difficulty.EASY -> "GATO"
            Difficulty.NORMAL -> "MOVIL"
            Difficulty.HARD -> "T0RMENTA"
        }
    }
}