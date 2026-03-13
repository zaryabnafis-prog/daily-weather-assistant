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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    onAboutClick: () -> Unit
) {

    val vm: WeatherViewModel = viewModel()
    var city by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Daily Weather Assistant")
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
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
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        if (vm.cityName.value.isNotEmpty()) {

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(24.dp)
                                ) {

                                    Text(
                                        text = vm.cityName.value,
                                        fontSize = 24.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = vm.temperature.value,
                                        fontSize = 32.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = vm.condition.value
                                    )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    AsyncImage(
                                        model = vm.iconUrl.value,
                                        contentDescription = null,
                                        modifier = Modifier.size(120.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        TextField(
                            value = city,
                            onValueChange = { city = it },
                            label = { Text("Enter city") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (city.isNotBlank()) {
                                    vm.loadWeather(city)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Search")
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                if (city.isNotBlank()) {
                                    vm.loadWeather(city)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Refresh")
                        }

                        if (city.isBlank()) {

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Please enter a city",
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onAboutClick,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = stringResource(R.string.about_button))
                        }
                    }
                }
            }
        }
    }
}