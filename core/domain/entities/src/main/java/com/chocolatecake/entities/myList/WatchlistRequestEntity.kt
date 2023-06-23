package com.chocolatecake.entities.myList



data class WatchlistRequestEntity(
    val watchlist: Boolean?,
    val mediaId: Int?,
    val mediaType: String,
)