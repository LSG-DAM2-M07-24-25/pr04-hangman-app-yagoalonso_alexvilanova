package com.example.pr4

sealed class Routes(val route: String) {
    object Launch : Routes("launch")
    object Menu : Routes("menu")
    object Game : Routes("game/{difficulty}") {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }
    object Result : Routes("result/{isGameWon}/{secretWord}/{attempts}") {
        fun createRoute(isGameWon: Boolean, secretWord: String, attempts: Int): String {
            return "result/$isGameWon/$secretWord/$attempts"
        }
    }
}