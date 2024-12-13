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
    var currentRoute by remember { mutableStateOf(Routes.Launch.route) }
    var selectedDifficulty by remember { mutableStateOf(Difficulty.NORMAL) }

    val launchViewModel = remember { LaunchViewModel() }
    val menuViewModel = remember { MenuViewModel() }
    val gameViewModel = remember { GameViewModel(MockWordRepository()) }
    val resultViewModel = remember { ResultViewModel() }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            val (screen, params) = Routes.parseRoute(currentRoute)
            when (screen) {
                "launch" -> LaunchScreen(
                    viewModel = launchViewModel,
                    onLaunchComplete = { currentRoute = Routes.Menu.route }
                )
                "menu" -> MenuScreen(
                    viewModel = menuViewModel,
                    onDifficultySelected = { difficulty ->
                        selectedDifficulty = difficulty
                        gameViewModel.startNewGame(difficulty)
                        currentRoute = Routes.Game.createRoute(difficulty.name)
                    }
                )
                "game" -> {
                    val difficulty = Difficulty.valueOf(params["difficulty"] ?: Difficulty.NORMAL.name)
                    GameScreen(
                        viewModel = gameViewModel,
                        onGameOver = {
                            resultViewModel.updateResultState(
                                isGameWon = gameViewModel.gameState.value.isGameWon,
                                secretWord = gameViewModel.gameState.value.secretWord,
                                attempts = gameViewModel.gameState.value.attempts
                            )
                            currentRoute = Routes.Result.createRoute(
                                isGameWon = gameViewModel.gameState.value.isGameWon,
                                secretWord = gameViewModel.gameState.value.secretWord,
                                attempts = gameViewModel.gameState.value.attempts
                            )
                        }
                    )
                }
                "result" -> {
                    val isGameWon = params["isGameWon"]?.toBoolean() ?: false
                    val secretWord = params["secretWord"] ?: ""
                    val attempts = params["attempts"]?.toInt() ?: 0
                    ResultScreen(
                        viewModel = resultViewModel,
                        onPlayAgain = {
                            gameViewModel.startNewGame(selectedDifficulty)
                            currentRoute = Routes.Game.createRoute(selectedDifficulty.name)
                        },
                        onReturnToMenu = { currentRoute = Routes.Menu.route }
                    )
                }
            }
        }
    }
}