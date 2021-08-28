package com.example.weatherapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.data.Location

class LocationAdapter(
    private val locations: List<Location>,
    private val onItemClicked: (Button) -> Unit
): RecyclerView.Adapter<LocationAdapter.LocationViewHolder>()
{

    class LocationViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_location)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.location_view, parent, false)

        return LocationViewHolder(layout)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location : Location = locations[position]

        holder.button.text = location.locationName

        holder.button.setOnClickListener {
            /*
            val action = WeatherForecastFragmentArgs(location = holder.button.text.toString()).toBundle()
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_searchPageFragment_to_weatherForecastFragment, action)

             */
            onItemClicked(it as Button)
        }
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}