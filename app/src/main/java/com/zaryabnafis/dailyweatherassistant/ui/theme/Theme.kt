package com.zaryabnafis.dailyweatherassistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlue,
    background = BackgroundLight
)

private val DarkColors = darkColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryBlue
)

@Composable
fun DailyWeatherAssistantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}