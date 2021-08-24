package com.example.weatherapplication


import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.network.WeatherApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.DoubleBinaryOperator

private const val API_KEY = "32549338c3473dfd66f5845f84344a29"
private const val EXCLUSIONS = "current,minutely,hourly,alerts"

class WeatherForecastViewModel : ViewModel() {

    fun returnText(location: String, myButton: Button) {
        viewModelScope.launch {
            try {
                //get current result only
                val currentResult =
                    WeatherApi.retrofitService.getWeather(location = location, key = API_KEY)

                myButton.text = currentResult.name

                val longi : Double= 2.3488
                val latti : Double= 48.8534

                val dailyResults = WeatherApi.retrofitService.getDaily(
                    latitude = latti,
                    longitude = longi,
                    exclusion = EXCLUSIONS,
                    key = API_KEY
                )
                // get learn weekdays for each day
                myButton.text = dailyResults.timezone


            } catch (e: Exception) {
                myButton.text = "error"
            }
        }
    }
}