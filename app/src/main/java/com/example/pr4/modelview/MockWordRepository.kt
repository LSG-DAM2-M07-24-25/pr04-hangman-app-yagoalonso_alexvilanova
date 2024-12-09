package com.example.pr4.repository

import com.example.pr4.models.Difficulty

class MockWordRepository : WordRepository {
    override fun getWordByDifficulty(difficulty: Difficulty): String {
        return when (difficulty) {
            Difficulty.EASY -> "CAT"
            Difficulty.NORMAL -> "APPLE"
            Difficulty.HARD -> "HANGMAN"
        }
    }
}