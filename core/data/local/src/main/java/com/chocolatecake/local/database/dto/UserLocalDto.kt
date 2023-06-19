package com.chocolatecake.local.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class UserLocalDto(
    @PrimaryKey(autoGenerate = false)
    val username: String,
    val peopleGameLevel: Int = 1,
    val numPeopleQuestionsPassed: Int = 0,
    val tvShowGameLevel: Int = 1,
    val numTvShowQuestionsPassed: Int = 0,
    val moviesGameLevel: Int = 1,
    val numMoviesQuestionsPassed: Int = 0,
    val memorizeGameLevel: Int = 1,
    val totalPoints: Int = 0
)
