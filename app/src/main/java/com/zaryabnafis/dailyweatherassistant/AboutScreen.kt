package com.zaryabnafis.dailyweatherassistant

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(
    onBackClick: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Daily Weather Assistant")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Shows current weather for any city")

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBackClick) {
                Text("Back")
            }
        }
    }
}