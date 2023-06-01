package com.chocolatecake.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UPCOMING_MOVIE_TABLE")
data class UpcomingMovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val rate: Double,
)

