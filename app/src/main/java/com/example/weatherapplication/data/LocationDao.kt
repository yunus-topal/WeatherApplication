package com.example.weatherapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getLocations(): Flow<List<Location>>

    @Query("SELECT * from location WHERE name = :name")
    fun getLocation(name: String): Flow<Location>

    @Query("DELETE FROM location WHERE  name = :name")
    suspend fun deleteLocation(name: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location: Location)

    @Delete
    suspend fun delete(location: Location)
}