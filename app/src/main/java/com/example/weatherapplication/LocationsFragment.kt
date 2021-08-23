package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.weatherapplication.databinding.FragmentLocationsBinding
import com.example.weatherapplication.databinding.FragmentSearchPageBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LocationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LocationsFragment : Fragment() {

    private var _binding: FragmentLocationsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationsBinding.inflate(inflater,container,false)

        binding.button3.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_locationsFragment_to_weatherForecastFragment)
        }
        return binding.root
    }

}