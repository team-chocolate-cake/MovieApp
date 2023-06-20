package com.chocolatecake.local.database.dto.myList

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LIST_TABLE")
data class ListLocalDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val itemCount: Int,
    val listType: String,
    val name: String,
)