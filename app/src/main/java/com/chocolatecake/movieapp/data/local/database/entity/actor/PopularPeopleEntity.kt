package com.chocolatecake.movieapp.data.local.database.entity.actor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "POPULAR_PEOPLE_TABLE")
data class PopularPeopleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val profilePath: String,
    val name: String
)


