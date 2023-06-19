package com.chocolatecake.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.local.database.dto.UserLocalDto

@Dao
interface TriviaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserLocalDto)


    @Query("SELECT * FROM USER_TABLE WHERE :username = username")
    suspend fun findUserByUsername(username: String): List<UserLocalDto>

    @Query("DELETE FROM USER_TABLE WHERE :username = username")
    suspend fun deleteUserByUsername(username: String)
}