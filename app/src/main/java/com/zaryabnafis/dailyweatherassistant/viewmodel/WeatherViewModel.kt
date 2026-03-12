package com.zaryabnafis.dailyweatherassistant.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaryabnafis.dailyweatherassistant.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    var cityName = mutableStateOf("")
    var temperature = mutableStateOf("")
    var condition = mutableStateOf("")
    var iconUrl = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf("")


    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                error.value = ""

                val weather = repository.getWeather(city)

                cityName.value = weather.location.name
                temperature.value = "${weather.current.temp_c} °C"
                condition.value = weather.current.condition.text
                iconUrl.value = "https:${weather.current.condition.icon}"

            } catch (e: Exception) {
                error.value = "Failed to load weather"
            } finally {
                isLoading.value = false
            }
        }
    }
}
