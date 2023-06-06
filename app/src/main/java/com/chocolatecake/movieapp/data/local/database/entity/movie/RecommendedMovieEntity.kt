package com.chocolatecake.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RECOMMENDED_MOVIE_TABLE")
data class RecommendedMovieEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val rate: Double,
        )