package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapplication.data.Location
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapplication.databinding.FragmentLocationsBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/**
 * A simple [Fragment] subclass.
 * Use the [LocationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    val binding get() = _binding!!

    private val locationViewModel: AddLocationViewModel by activityViewModels {
        AddLocationViewModelFactory(
            (activity?.application as WeatherForecastApplication).database.locationDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationsBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       val listOfLocations = runBlocking {   locationViewModel.getAllLocations() }

        // val flatList: List<Location> = runBlocking {  listOfLocations.flattenToList() }

        //val flatList = listOf<Location>(Location( locationName = "manchester"), Location(locationName = "london"), Location(locationName = "liverpool"),
        //Location(locationName = "ankara"))

        val adapter = LocationAdapter(listOfLocations) {
            val action = WeatherForecastFragmentArgs(location = it.text.toString()).toBundle()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_locationsFragment_to_weatherForecastFragment, action)
            /*
     val action = LocationsFragmentDirections.actionLocationsFragmentToWeatherForecastFragment()
     this.findNavController().navigate(action)
     */
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

    }
}