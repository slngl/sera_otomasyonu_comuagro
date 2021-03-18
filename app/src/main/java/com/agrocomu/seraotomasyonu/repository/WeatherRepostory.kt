package com.agrocomu.seraotomasyonu.repository

import com.agrocomu.seraotomasyonu.base.BaseRepostory
import com.agrocomu.seraotomasyonu.entity.WeatherResponse
import com.agrocomu.seraotomasyonu.entity.base.DataHolder
import com.agrocomu.seraotomasyonu.network.WeatherApi
import javax.inject.Inject

class WeatherRepostory @Inject constructor(private val weatherApi: WeatherApi) : BaseRepostory() {

    suspend fun getWeatherData(cityName: String): DataHolder<WeatherResponse> {
        return safeApiCall({ weatherApi.getWeather(cityName, "tr") }, "weather error message")
    }
}