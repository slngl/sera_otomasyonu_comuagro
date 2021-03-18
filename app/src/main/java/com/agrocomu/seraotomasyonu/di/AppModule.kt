package com.agrocomu.seraotomasyonu.di

import com.agrocomu.seraotomasyonu.di.qualifier.BaseURLQualifier
import com.agrocomu.seraotomasyonu.di.qualifier.WaetherURLQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    @WaetherURLQualifier
    fun provideBaseUrl(): String = "https://api.openweathermap.org/data/"

}