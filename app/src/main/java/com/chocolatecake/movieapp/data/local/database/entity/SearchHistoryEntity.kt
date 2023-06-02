package com.chocolatecake.movieapp.data.local.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SEARCH_HISTORY_TABLE" )
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = false)
    val keyword: String,
)
