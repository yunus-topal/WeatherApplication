package com.example.weatherapplication



import android.view.View
import android.widget.Button
import android.widget.TextView
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
import kotlin.math.min
import kotlin.math.truncate

private const val API_KEY = "32549338c3473dfd66f5845f84344a29"
private const val EXCLUSIONS = "current,minutely,hourly,alerts"
private const val UNIT = "metric"
private var dailyResults: DailyForecast? = null
class WeatherForecastViewModel : ViewModel() {

    fun returnText(location: String, myButton: Button, weekDays: List<TextView>, maxDegrees: List<TextView>, minDegrees: List<TextView>) {
        viewModelScope.launch {
            try {
                //get current result only
                val currentResult =
                    WeatherApi.retrofitService.getWeather(location = location, key = API_KEY, unit = UNIT)
                val long : Double = String.format("%.3f", currentResult.coord.lon).toDouble()
                val latt : Double = String.format("%.3f", currentResult.coord.lat).toDouble()

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
                fillDayTextViews(weekDays, maxDegrees, minDegrees)
            } catch (e: Exception) {
                dailyResults = null
                weekDays[0].text = "Error"
            }
        }
    }

    private fun fillDayTextViews(weekDays: List<TextView>, maxDegrees: List<TextView>, minDegrees: List<TextView>){
        if(dailyResults != null){
            // Fill weekdays
            for(i in 0..4){
                weekDays[i].text = getDateTime(dailyResults!!.daily[i].dt.toString())
            }
            // Fill max degrees
            for(i in 0..4){
                maxDegrees[i].text = truncate(dailyResults!!.daily[i].temp.day).toInt().toString()
            }
            for(i in 0..4){
                minDegrees[i].text = truncate(dailyResults!!.daily[i].temp.night).toInt().toString()
            }
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