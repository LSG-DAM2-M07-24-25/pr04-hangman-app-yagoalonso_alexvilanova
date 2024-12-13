package com.example.pr4.modelview

import androidx.lifecycle.ViewModel
import com.example.pr4.models.Difficulty
import com.example.pr4.models.MenuModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MenuViewModel : ViewModel() {
    private val _menuState = MutableStateFlow(MenuModel())
    val menuState = _menuState.asStateFlow()

    fun selectDifficulty(difficulty: Difficulty) {
        _menuState.update { it.copy(selectedDifficulty = difficulty) }
    }
}
