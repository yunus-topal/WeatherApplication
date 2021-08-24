package com.example.weatherapplication.network


data class DailyForecast(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val daily: List<Daily>
)

data class Daily(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val moonrise: Int,
    val moonset: Int,
    val moon_phase: Double,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val wind_gust: Double,
    val weather: List<Weather>,
    val clouds: Int,
    val pop: Int,
    val uvi: Double
)


data class FeelsLike (
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Temp (
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

//CURRENT WEATHER FORECAST PART (Weather class is used by both of them)

data class WeatherForecast(
    val coord : Coord,
    val weather : List<Weather>,
    val base : String,
    val main : Main,
    val visibility : Int,
    val wind : Wind,
    val clouds : Clouds,
    val dt : Int,
    val sys : Sys,
    val timezone : Int,
    val id : Int,
    val name : String,
    val cod : Int
)

data class Coord (

    val lon : Double,
    val lat : Double
)

data class Weather (

    val id : Int,
    val main : String,
    val description : String,
    val icon : String
)


data class Main (

    val temp : Double,
    val feels_like : Double,
    val temp_min : Double,
    val temp_max : Double,
    val pressure : Int,
    val humidity : Int
)

data class Wind (

    val speed : Double,
    val deg : Int
)

data class Clouds (

    val all : Int
)

data class Sys (

    val type : Int,
    val id : Int,
    val country : String,
    val sunrise : Int,
    val sunset : Int
)





