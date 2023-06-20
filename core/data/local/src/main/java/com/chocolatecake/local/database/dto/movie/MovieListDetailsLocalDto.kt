package com.chocolatecake.local.database.dto.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE_LIST_DETAILS_TABLE")
data class MovieListDetailsLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val title: String,
    val rate: Double,
    val year: String,
    val mediaType: String? ="movie"
)