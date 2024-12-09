package com.example.pr4.models

data class MenuModel(
    val availableDifficulties: List<Difficulty> = Difficulty.values().toList(),
    val selectedDifficulty: Difficulty = Difficulty.NORMAL
)