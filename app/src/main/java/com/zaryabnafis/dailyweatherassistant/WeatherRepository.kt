package com.zaryabnafis.dailyweatherassistant

import com.zaryabnafis.dailyweatherassistant.network.RetrofitInstance
import com.zaryabnafis.dailyweatherassistant.data.model.WeatherResponse

class WeatherRepository {

    private val apiKey = "3660e1bcc4f34080a54162040260303"

    suspend fun getWeather(): WeatherResponse {
        return RetrofitInstance.api.getWeather(
            apiKey = apiKey,
            city = "Oulu"
        )
    }
}
