package com.example.weatherapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)
