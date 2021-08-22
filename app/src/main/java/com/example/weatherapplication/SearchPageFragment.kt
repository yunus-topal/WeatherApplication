package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.weatherapplication.databinding.FragmentSearchPageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SearchPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchPageFragment : Fragment() {

    private var _binding: FragmentSearchPageBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchPageBinding.inflate(inflater,container,false)

        binding.submitButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_searchPageFragment_to_weatherForecastFragment)
        }
        return binding.root
    }

}