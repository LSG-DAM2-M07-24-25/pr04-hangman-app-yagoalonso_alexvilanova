package com.example.pr4.models

class MockWordRepository : WordRepository() {
    override fun getWordByDifficulty(difficulty: Difficulty): String {
        return when (difficulty) {
            Difficulty.FACIL -> "GATO"
            Difficulty.NORMAL -> "TORMENTOSO"
            Difficulty.DIFICIL -> "ENCEFALOGRAMA"
        }
    }
}
