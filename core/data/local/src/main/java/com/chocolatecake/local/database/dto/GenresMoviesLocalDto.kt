package com.chocolatecake.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GENRES_MOVIES_TABLE")
data class GenresMoviesLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
