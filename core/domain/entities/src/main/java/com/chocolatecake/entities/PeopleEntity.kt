package com.chocolatecake.entities

data class PeopleEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val popularity: Double = 0.0
)
