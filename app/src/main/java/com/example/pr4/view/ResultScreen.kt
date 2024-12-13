package com.example.pr4.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pr4.modelview.ResultViewModel

@Composable
fun ResultScreen(
    viewModel: ResultViewModel,
    onPlayAgain: () -> Unit,
    onReturnToMenu: () -> Unit
) {
    val resultState by viewModel.resultState.collectAsState()

    val resultText = if (resultState.isGameWon) {
        "¡Felicidades! Adivinaste la palabra en ${resultState.attempts} intentos."
    } else {
        "Mala suerte. La palabra era: ${resultState.secretWord}"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = resultText,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onPlayAgain) {
            Text("Jugar de nuevo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onReturnToMenu) {
            Text("Volver al menú principal")
        }
    }
}