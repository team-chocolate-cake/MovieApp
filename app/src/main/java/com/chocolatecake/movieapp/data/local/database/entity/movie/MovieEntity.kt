package com.chocolatecake.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE_TABLE")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)