package com.agrocomu.seraotomasyonu.di

import com.agrocomu.seraotomasyonu.di.qualifier.WaetherURLQualifier
import com.agrocomu.seraotomasyonu.network.WeatherApi
import com.agrocomu.seraotomasyonu.network.interceptors.WeatherQueryInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    /*    @Provides
        @Singleton
        fun provideApiService(
            okHttpClient: OkHttpClient,
            @BaseURLQualifier baseURL: String,
            gson: Gson
        ): ApiService {
            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseURL)
                .build()
                .create(ApiService::class.java)*/

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        weatherQueryInterceptor: WeatherQueryInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(weatherQueryInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideQueryIntercepter(): WeatherQueryInterceptor {
        return WeatherQueryInterceptor()
    }


    @Provides
    @Singleton
    fun provideWeatherApiService(
        okHttpClient: OkHttpClient, @WaetherURLQualifier weatherURL: String,
        moshiConverterFactory: MoshiConverterFactory
    ): WeatherApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(weatherURL)
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

}
