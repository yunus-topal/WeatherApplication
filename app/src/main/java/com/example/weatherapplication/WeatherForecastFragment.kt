package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.weatherapplication.databinding.FragmentWeatherForecastBinding
import android.view.View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION
import android.widget.TextView


private const val WEATHER_LOCATION = "location"
private const val WEEKDAY_TAG = "weekday"
private const val MAX_DEGREE_TAG = "maxdegree"
private const val MIN_DEGREE_TAG = "mindegree"

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


        val weekdayViews: ArrayList<View> = ArrayList()
        binding.root.findViewsWithText(weekdayViews, WEEKDAY_TAG, FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        val textWeekdayViews: ArrayList<TextView> = weekdayViews as ArrayList<TextView>

        val maxDegreeViews: ArrayList<View> = ArrayList()
        binding.root.findViewsWithText(maxDegreeViews, MAX_DEGREE_TAG, FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        val textMaxDegreeViews: ArrayList<TextView> = maxDegreeViews as ArrayList<TextView>

        val minDegreeViews: ArrayList<View> = ArrayList()
        binding.root.findViewsWithText(minDegreeViews, MIN_DEGREE_TAG, FIND_VIEWS_WITH_CONTENT_DESCRIPTION)
        val textMinDegreeViews: ArrayList<TextView> = minDegreeViews as ArrayList<TextView>

        val myButton = binding.button
        viewModel.returnText(location!!, myButton ,textWeekdayViews,textMaxDegreeViews,textMinDegreeViews)

        return binding.root
    }




}