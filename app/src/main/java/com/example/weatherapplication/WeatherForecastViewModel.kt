package com.example.weatherapplication



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.databinding.FragmentWeatherForecastBinding
import com.example.weatherapplication.network.DailyForecast
import com.example.weatherapplication.network.WeatherApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.truncate

private const val API_KEY = "32549338c3473dfd66f5845f84344a29"
private const val EXCLUSIONS = "current,minutely,hourly,alerts"
private const val UNIT = "metric"
private var dailyResults: DailyForecast? = null
class WeatherForecastViewModel : ViewModel() {

    fun returnText(location: String, binding: FragmentWeatherForecastBinding) {
        viewModelScope.launch {
            try {
                val myButton = binding.button
                //get current result only
                val currentResult =
                    WeatherApi.retrofitService.getWeather(location = location, key = API_KEY, unit = UNIT)
                val long : Double = currentResult.coord.lon
                val latt : Double = currentResult.coord.lat

                myButton.text = currentResult.name

                val dailies: DailyForecast = WeatherApi.retrofitService.getDaily(
                    latitude = latt,
                    longitude = long,
                    exclusion = EXCLUSIONS,
                    key = API_KEY,
                    unit = UNIT
                )
                dailyResults = dailies
                // get learn weekdays for each day
                fillDayViews(binding)
            } catch (e: Exception) {
                val myButton = binding.button
                dailyResults = null
                binding.weekday.text = "Error"
            }
        }
    }

    fun fillDayViews(binding: FragmentWeatherForecastBinding){
        if(dailyResults != null){
            binding.weekday.text = getDateTime(dailyResults!!.daily[0].dt.toString())
            binding.maxDegree.text = truncate(dailyResults!!.daily[0].temp.max).toInt().toString()
            binding.minDegree.text = truncate(dailyResults!!.daily[0].temp.min).toInt().toString()
        }
    }
    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("EEEE")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}