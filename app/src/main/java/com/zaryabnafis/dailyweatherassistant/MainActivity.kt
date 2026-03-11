package com.zaryabnafis.dailyweatherassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.zaryabnafis.dailyweatherassistant.ui.theme.DailyWeatherAssistantTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DailyWeatherAssistantTheme {

                var showAbout by remember { mutableStateOf(false) }

                if (showAbout) {
                    AboutScreen(onBackClick = { showAbout = false })
                } else {
                    WeatherScreen(onAboutClick = { showAbout = true })
                }

            }
        }
    }
}
