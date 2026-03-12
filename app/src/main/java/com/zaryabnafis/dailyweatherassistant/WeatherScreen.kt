package com.zaryabnafis.dailyweatherassistant

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.zaryabnafis.dailyweatherassistant.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    onAboutClick: () -> Unit
) {

    val vm: WeatherViewModel = viewModel()

    var city by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when {

            vm.isLoading.value -> {
                CircularProgressIndicator()
            }

            vm.error.value.isNotEmpty() -> {
                Text(vm.error.value)
            }

            else -> {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {

                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("Enter city") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (city.isNotBlank()) {
                                vm.loadWeather(city)
                            }
                        }
                    ) {
                        Text("Search")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (city.isNotBlank()) {
                                vm.loadWeather(city)
                            }
                        }
                    ) {
                        Text("Refresh")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    if (city.isBlank()) {
                        Text(
                            text = "Please enter a city",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    if (vm.cityName.value.isEmpty()) {

                        Text(
                            text = "Search for a city to see the current weather",
                            fontSize = 18.sp
                        )

                    } else {

                        Text(
                            text = vm.cityName.value,
                            fontSize = 24.sp
                        )

                        Text(
                            text = vm.temperature.value,
                            fontSize = 32.sp
                        )

                        Text(
                            text = vm.condition.value
                        )

                        AsyncImage(
                            model = vm.iconUrl.value,
                            contentDescription = null,
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(onClick = onAboutClick) {
                        Text(text = stringResource(R.string.about_button))
                    }
                }
            }
        }
    }
}