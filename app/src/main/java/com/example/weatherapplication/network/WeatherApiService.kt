package com.example.weatherapplication.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.DoubleBuffer

private const val BASE_URL =
    "https://api.openweathermap.org"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appid") key: String,
        @Query("units") unit: String
    ) : WeatherForecast

    @GET("data/2.5/onecall")
    suspend fun getDaily(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclusion: String,
        @Query("appid") key: String,
        @Query("units") unit: String
    ): DailyForecast
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object WeatherApi {
    val retrofitService: WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}