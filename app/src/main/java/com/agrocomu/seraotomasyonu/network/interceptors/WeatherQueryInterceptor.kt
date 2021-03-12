package com.agrocomu.seraotomasyonu.network.interceptors

import com.agrocomu.seraotomasyonu.base.AppConstants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class WeatherQueryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url.newBuilder()
            .addQueryParameter("APPID", AppConstants.OPEN_WEATHER_API_KEY)
            .addQueryParameter("mode", "json")
            .addQueryParameter("units", "metric")
            .build()
        return chain.proceed(
            chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build()
        )
    }
}