package com.chocolatecake.local.database.dto.myList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LIST_MOVIE_TABLE")
data class ListMovieLocalDto(
    @PrimaryKey(autoGenerate = false)
    val mediaId: Int,
    val listId: Int,
    val mediaType: String,
    val nameList: String,
    val posterPath: String,
)