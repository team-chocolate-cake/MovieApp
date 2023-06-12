package com.chocolatecake.movieapp.data.local.database.dto.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RECOMMENDED_MOVIE_TABLE")
data class RecommendedMovieLocalDto (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val rate: Double,
    val title: String,
)