package com.example.weatherapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.weatherapplication.databinding.FragmentSearchPageBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [SearchPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val EMPTY_INPUT_ERROR_MESSAGE = "Type something first!"
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
            takeInput()
        }
        binding.locationsButton
            .setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_searchPageFragment_to_locationsFragment)
        }
        return binding.root
    }
    private fun takeInput(){
        val targetLocation = binding.textInputLayout.editText?.text.toString()
        if(targetLocation == ""){
            val snackbar = Snackbar
                .make(binding.root, EMPTY_INPUT_ERROR_MESSAGE, Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
        else {
            val action = WeatherForecastFragmentArgs(location = targetLocation).toBundle()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_searchPageFragment_to_weatherForecastFragment, action)
        }
    }
}