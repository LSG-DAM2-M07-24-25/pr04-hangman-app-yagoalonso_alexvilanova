package com.example.pr4

import com.example.pr4.models.Difficulty


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

    companion object {
        fun parseRoute(route: String): Pair<String, Map<String, String>> {
            val segments = route.split("/")
            return when {
                route.startsWith("game") -> {
                    val difficulty = segments.getOrNull(1) ?: Difficulty.NORMAL.name
                    "game" to mapOf("difficulty" to difficulty)
                }
                route.startsWith("result") -> {
                    val isGameWon = segments.getOrNull(1)?.toBooleanStrictOrNull() ?: false
                    val secretWord = segments.getOrNull(2) ?: ""
                    val attempts = segments.getOrNull(3)?.toIntOrNull() ?: 0
                    "result" to mapOf(
                        "isGameWon" to isGameWon.toString(),
                        "secretWord" to secretWord,
                        "attempts" to attempts.toString()
                    )
                }
                else -> route to emptyMap()
            }
        }
    }
}