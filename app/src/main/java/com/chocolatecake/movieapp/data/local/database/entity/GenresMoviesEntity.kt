package com.chocolatecake.movieapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENRES_MOVIES_TABLE")
data class GenresMoviesEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String
)
