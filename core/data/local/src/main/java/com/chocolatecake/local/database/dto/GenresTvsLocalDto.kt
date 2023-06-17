package com.chocolatecake.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENRES_TVS_TABLE")
data class GenresTvsLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)