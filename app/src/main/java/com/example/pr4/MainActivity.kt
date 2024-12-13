package com.example.pr4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.pr4.models.Difficulty
import com.example.pr4.models.MockWordRepository
import com.example.pr4.modelview.*
import com.example.pr4.views.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    var currentScreen by remember { mutableStateOf("launch") }
    var selectedDifficulty by remember { mutableStateOf(Difficulty.NORMAL) }

    val launchViewModel = remember { LaunchViewModel() }
    val menuViewModel = remember { MenuViewModel() }
    val gameViewModel = remember { GameViewModel(MockWordRepository()) }
    val resultViewModel = remember { ResultViewModel() }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                "launch" -> LaunchScreen(
                    viewModel = launchViewModel,
                    onLaunchComplete = { currentScreen = "menu" }
                )
                "menu" -> MenuScreen(
                    viewModel = menuViewModel,
                    onDifficultySelected = { difficulty ->
                        selectedDifficulty = difficulty
                        gameViewModel.startNewGame(difficulty)
                        currentScreen = "game"
                    }
                )
                "game" -> GameScreen(
                    viewModel = gameViewModel,
                    onGameOver = {
                        resultViewModel.updateResultState(
                            isGameWon = gameViewModel.gameState.value.isGameWon,
                            secretWord = gameViewModel.gameState.value.secretWord,
                            attempts = gameViewModel.gameState.value.attempts
                        )
                        currentScreen = "result"
                    }
                )
                "result" -> ResultScreen(
                    viewModel = resultViewModel,
                    onPlayAgain = {
                        gameViewModel.startNewGame(selectedDifficulty)
                        currentScreen = "game"
                    },
                    onReturnToMenu = {
                        currentScreen = "menu"
                    }
                )
            }
        }
    }
}
