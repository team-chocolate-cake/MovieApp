package com.chocolatecake.entities.myList



data class FavoriteBodyRequestEntity(
    val favorite: Boolean?,
    val mediaId: Int?,
    val mediaType: String,
)