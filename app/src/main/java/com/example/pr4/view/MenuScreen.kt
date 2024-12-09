package com.example.pr4.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pr4.models.Difficulty
import com.example.pr4.viewmodels.MenuViewModel
import com.example.pr4.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    onDifficultySelected: (Difficulty) -> Unit
) {
    val menuState by viewModel.menuState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var showHelpDialog by remember { mutableStateOf(false) }

    if (showHelpDialog) {
        AlertDialog(
            onDismissRequest = { showHelpDialog = false },
            title = { Text("Instrucciones del juego") },
            text = {
                Text(
                    "¡Bienvenido al Juego del Ahorcado!\n\n" +
                            "Objetivo: Adivina la palabra oculta antes de quedarte sin intentos.\n\n" +
                            "Cómo jugar:\n" +
                            "- Elige una dificultad (Fácil, Normal, Difícil)\n" +
                            "- Haz clic en las letras para adivinar la palabra\n" +
                            "- Cada vez que falles, el ahorcado se completará\n" +
                            "- Tienes 7 intentos para adivinar la palabra\n\n" +
                            "¡Ganas si adivinas la palabra completa!",
                    textAlign = TextAlign.Start
                )
            },
            confirmButton = {
                TextButton(onClick = { showHelpDialog = false }) {
                    Text("Entendido")
                }
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.hangman_logo),
            contentDescription = "Logo del Ahorcado",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text("Selecciona Dificultad", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.width(250.dp)
            ) {
                TextField(
                    value = menuState.selectedDifficulty.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Dificultad") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    menuState.availableDifficulties.forEach { difficulty ->
                        DropdownMenuItem(
                            text = { Text(difficulty.name) },
                            onClick = {
                                viewModel.selectDifficulty(difficulty)
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onDifficultySelected(menuState.selectedDifficulty) },
            modifier = Modifier.width(250.dp)
        ) {
            Text("Jugar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showHelpDialog = true },
            modifier = Modifier.width(250.dp)
        ) {
            Text("Ayuda")
        }
    }
}