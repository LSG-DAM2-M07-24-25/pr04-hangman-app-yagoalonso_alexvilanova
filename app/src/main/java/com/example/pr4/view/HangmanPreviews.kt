package com.example.pr4.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pr4.models.Difficulty
import com.example.pr4.repository.MockWordRepository
import com.example.pr4.viewmodels.ResultViewModel

@Preview(showBackground = true)
@Composable
fun ResultScreenPreviewWin() {
    val resultViewModel = ResultViewModel()
    resultViewModel.updateResultState(
        isGameWon = true,
        secretWord = "EJEMPLO",
        attempts = 3
    )

    ResultScreen(
        viewModel = resultViewModel,
        onPlayAgain = {},
        onReturnToMenu = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreviewLose() {
    val resultViewModel = ResultViewModel()
    resultViewModel.updateResultState(
        isGameWon = false,
        secretWord = "EJEMPLO",
        attempts = 7
    )

    ResultScreen(
        viewModel = resultViewModel,
        onPlayAgain = {},
        onReturnToMenu = {}
    )
}