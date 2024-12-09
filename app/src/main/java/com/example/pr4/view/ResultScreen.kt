package com.example.pr4.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr4.viewmodels.ResultViewModel

@Composable
fun ResultScreen(
    viewModel: ResultViewModel,
    onPlayAgain: () -> Unit,
    onReturnToMenu: () -> Unit
) {
    val resultState by viewModel.resultState.collectAsState()

    val headerText = if (resultState.isGameWon) {
        "¡Felicidades!"
    } else {
        "¡Qué pena!"
    }

    val messageText = if (resultState.isGameWon) {
        "Has adivinado la palabra en ${resultState.attempts} intentos."
    } else {
        "No has adivinado la palabra. La palabra era: ${resultState.secretWord.uppercase()}."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = headerText,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = messageText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = onPlayAgain,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Volver a Jugar")
        }

        Button(
            onClick = onReturnToMenu,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Menú Principal")
        }
    }
}