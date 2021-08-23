package com.example.weatherapplication

import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.network.WeatherApi
import kotlinx.coroutines.launch
import java.lang.Exception


class WeatherForecastViewModel: ViewModel() {

    fun returnText(myButton: Button) {
        viewModelScope.launch {
            try{
                val result = WeatherApi.retrofitService.getWeather()
                myButton.text = result.title
            } catch (e: Exception){
                myButton.text = "error"
            }

        }
    }
}