package com.agrocomu.seraotomasyonu.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agrocomu.seraotomasyonu.entity.WeatherResponse
import com.agrocomu.seraotomasyonu.entity.base.DataHolder
import com.agrocomu.seraotomasyonu.entity.base.mapAndResponseIfSuccess
import com.agrocomu.seraotomasyonu.repository.WeatherRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepostory: WeatherRepostory) :
    ViewModel() {

    val liveWeatherResponse = MutableLiveData<WeatherResponse>()
    val liveSpeed = MutableLiveData<String>()
    val liveTemp = MutableLiveData<String>()
    val liveDesc = MutableLiveData<String>()
    val liveIcon = MutableLiveData<String>()
    val liveError = MutableLiveData<String>()

    fun getWeather(cityName: String) {

        viewModelScope.launch {
            val response = weatherRepostory.getWeatherData(cityName)
            when (response) {
                is DataHolder.Success -> liveWeatherResponse.postValue(response.data!!)
                is DataHolder.Error -> liveError.postValue(response.message)
            }
/*            response.mapAndResponseIfSuccess {
                liveWeatherResponse.postValue(it)
                liveTemp.postValue(it.main?.temp.toString())
                liveDesc.postValue(it.weather!![0].description!!)
                liveSpeed.postValue(it.wind?.speed.toString())
            }*/
        }

    }
}