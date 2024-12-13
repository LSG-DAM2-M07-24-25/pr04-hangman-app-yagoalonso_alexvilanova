package com.example.pr4.models


open class WordRepository {
    open fun getWordByDifficulty(difficulty: Difficulty): String {
        return when (difficulty) {
            Difficulty.FACIL -> "GATO"
            Difficulty.NORMAL -> "TORMENTOSO"
            Difficulty.DIFICIL -> "ENCEFALOGRAMA"
        }
    }
}