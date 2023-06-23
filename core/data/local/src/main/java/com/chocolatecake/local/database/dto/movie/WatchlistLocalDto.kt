package com.chocolatecake.local.database.dto.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WATCHLIST_TABLE")
data class WatchlistLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imageUrl: String,
    val title: String,
    val rate: Double,
    val year: String,
    val mediaType: String = "movies",
)