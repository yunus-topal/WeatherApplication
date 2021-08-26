package com.example.weatherapplication

import android.app.Application
import com.example.weatherapplication.data.LocationRoomDatabase

class WeatherForecastApplication: Application() {

    val database: LocationRoomDatabase by lazy {LocationRoomDatabase.getDatabase(this)}
}