package com.agrocomu.seraotomasyonu.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agrocomu.seraotomasyonu.entity.WeatherResponse
import com.agrocomu.seraotomasyonu.entity.base.DataHolder
import com.agrocomu.seraotomasyonu.repository.WeatherRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepostory: WeatherRepostory) : ViewModel() {

    val liveWeatherResponse = MutableLiveData<WeatherResponse>()
    val liveError = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getWeather(cityName: String) {

        viewModelScope.launch {
            val response = weatherRepostory.getWeatherData(cityName)
            when (response) {
                is DataHolder.Success -> liveWeatherResponse.postValue(response.data!!)
                is DataHolder.Error -> liveError.postValue(response.message)
            }
        }
    }
}