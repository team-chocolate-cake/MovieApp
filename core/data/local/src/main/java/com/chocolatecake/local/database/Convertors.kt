package com.chocolatecake.local.database

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun longToDate(long: Long): Date {
        return Date(long)
    }

}