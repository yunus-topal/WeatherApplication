package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.databinding.FragmentWeatherForecastBinding
import com.example.weatherapplication.network.WeatherApi
import kotlinx.coroutines.launch
import java.lang.Exception


private const val WEATHER_LOCATION = "location"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherForecastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherForecastFragment : Fragment() {
    private val viewModel: WeatherForecastViewModel by viewModels()
    private var location: String? = null

    private var _binding: FragmentWeatherForecastBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            location = it.getString(WEATHER_LOCATION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherForecastBinding.inflate(inflater,container,false)

        val myButton = binding.button
        viewModel.returnText(location!!, binding)
        return binding.root
    }



}