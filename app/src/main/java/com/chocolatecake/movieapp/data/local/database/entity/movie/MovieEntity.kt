package com.chocolatecake.movieapp.data.local.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE_TABLE")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
//    val adult: Boolean?,
//    val backdropPath: String?,
//    val genreIds: List<Int?>?,
//    val originalLanguage: String?,
//    val originalTitle: String?,
//    val overview: String?,
//    val popularity: Double?,
    val posterPath: String?,
//    val releaseDate: String?,
    val title: String?,
//    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)