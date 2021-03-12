package com.agrocomu.seraotomasyonu.network

import com.agrocomu.seraotomasyonu.entity.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //https://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=5ff5f6121eced2f3ad373070cbbb2040&units=metric777

    @GET("2.5/weather")
    suspend fun getWeather(@Query("q") cityName: String, @Query ("lang") lang: String): Response<WeatherResponse>


}