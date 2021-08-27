package com.example.weatherapplication



import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.weatherapplication.databinding.FragmentWeatherForecastBinding
import com.example.weatherapplication.network.DailyForecast
import com.example.weatherapplication.network.WeatherApi
import com.example.weatherapplication.network.WeatherForecast
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
private const val IMAGE_URL_HEAD = "http://openweathermap.org/img/wn/"
private const val IMAGE_URL_TAIL = "@2x.png"

private var dailyResults: DailyForecast? = null

class WeatherForecastViewModel : ViewModel() {

    suspend fun returnText(location: String, currentViews :List<TextView>, currentImage: ImageView): Boolean {
        var isValid = true
   //     viewModelScope.launch {
            try {
                /*
                CURRENT RESULTS
                 */
                val currentResult =
                    WeatherApi.retrofitService.getWeather(location = location, key = API_KEY, unit = UNIT)
                val long : Double = String.format("%.3f", currentResult.coord.lon).toDouble()
                val latt : Double = String.format("%.3f", currentResult.coord.lat).toDouble()

                fillCurrentWeather(currentResult,currentViews)

                val currentIconUrl = IMAGE_URL_HEAD + currentResult.weather[0].icon + IMAGE_URL_TAIL
                val currentIconUri = currentIconUrl.toUri().buildUpon().scheme("https").build()
                currentImage.load(currentIconUri)

                /*
                DAILY RESULTS
                 */
                val dailies: DailyForecast = WeatherApi.retrofitService.getDaily(
                    latitude = latt,
                    longitude = long,
                    exclusion = EXCLUSIONS,
                    key = API_KEY,
                    unit = UNIT
                )
                dailyResults = dailies

                isValid = true
            } catch (e: Exception) {
                dailyResults = null
                currentViews[0].text = "Error"
                isValid = false
            }
     //   }
        return isValid
    }

    fun fillDailyWeather(weekDays: List<TextView>, maxDegrees: List<TextView>, minDegrees: List<TextView>, iconViews: List<ImageView>){
        if(dailyResults != null){
            // Fill weekdays
            weekDays[0].text = "Today"
            for(i in 1..4){
                weekDays[i].text = getDateTime(dailyResults!!.daily[i].dt.toString())
            }
            // Fill max degrees
            for(i in 0..4){
                val maxdegree = truncate(dailyResults!!.daily[i].temp.day).toInt().toString() + "°"
                maxDegrees[i].text = maxdegree
            }
            for(i in 0..4){
                val mindegree = truncate(dailyResults!!.daily[i].temp.night).toInt().toString() + "°"
                minDegrees[i].text = mindegree
            }
            for(i in 0..4){
                val iconUrl = IMAGE_URL_HEAD + dailyResults!!.daily[i].weather[0].icon + IMAGE_URL_TAIL
                val iconUri = iconUrl.toUri().buildUpon().scheme("https").build()
                iconViews[i].load(iconUri)
            }
        }
    }

    private fun fillCurrentWeather(currentResult: WeatherForecast, currentViews: List<TextView>){
        currentViews[0].text = currentResult.name
        val currentDegree = truncate(currentResult.main.temp).toInt().toString() + "°"
        currentViews[1].text = currentDegree
        currentViews[2].text = currentResult.weather[0].description
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