package com.example.pr4.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pr4.R
import com.example.pr4.modelview.GameViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel,
    onGameOver: () -> Unit
) {
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(gameState.isGameWon, gameState.isGameLost) {
        if (gameState.isGameWon || gameState.isGameLost) {
            onGameOver()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HangmanImage(attempts = gameState.attempts)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = gameState.revealedWord,
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Intentos restantes: ${gameState.maxAttempts - gameState.attempts}")

        Spacer(modifier = Modifier.height(16.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ('A'..'Z').forEach { letter -> // Usar letras en mayúsculas
                Button(
                    onClick = { viewModel.guessLetter(letter) },
                    enabled = !gameState.usedLetters.contains(letter),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(letter.toString()) // Mostrar letra en mayúscula
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar intentos realizados
        Text(
            text = "Intentos realizados: ${gameState.attempts}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun HangmanImage(attempts: Int) {
    val hangmanImageResource = when (attempts) {
        0 -> R.drawable.hangman0
        1 -> R.drawable.hangman1
        2 -> R.drawable.hangman2
        3 -> R.drawable.hangman3
        4 -> R.drawable.hangman4
        5 -> R.drawable.hangman5
        6 -> R.drawable.hangman6
        else -> R.drawable.hangman7
    }

    Image(
        painter = painterResource(id = hangmanImageResource),
        contentDescription = "Imagen del Ahorcado",
        modifier = Modifier.size(200.dp)
    )
}