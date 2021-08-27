package com.example.weatherapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapplication.data.Location
import com.example.weatherapplication.data.LocationDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddLocationViewModel(private val locationDao: LocationDao) : ViewModel() {

    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(locationName: String) {

        val newItem = Location(locationName = locationName.lowercase())
        insertItem(newItem)

    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: Location) {
        viewModelScope.launch {
            locationDao.deleteLocation(item.locationName)
            locationDao.insert(item)
        }
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class AddLocationViewModelFactory(private val locationDao: LocationDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddLocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddLocationViewModel(locationDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}