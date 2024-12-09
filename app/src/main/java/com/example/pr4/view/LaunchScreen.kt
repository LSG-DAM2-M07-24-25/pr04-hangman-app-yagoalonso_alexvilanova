package com.example.pr4.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pr4.viewmodels.LaunchViewModel
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(
    viewModel: LaunchViewModel,
    onLaunchComplete: () -> Unit
) {
    val launchState by viewModel.launchState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = launchState.logoResourceId),
            contentDescription = "Logo del Ahorcado",
            modifier = Modifier.size(200.dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(launchState.splashDuration)
        onLaunchComplete()
    }
}