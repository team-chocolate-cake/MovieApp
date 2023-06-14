package com.chocolatecake.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "POPULAR_PEOPLE_TABLE")
data class PopularPeopleLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val imagerUrl: String,
    val name: String,
)


