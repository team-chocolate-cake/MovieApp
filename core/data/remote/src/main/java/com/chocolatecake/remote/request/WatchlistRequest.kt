package com.chocolatecake.remote.request


import com.google.gson.annotations.SerializedName

data class WatchlistRequest(
    @SerializedName("watchlist")
    val watchlist: Boolean? = null,
    @SerializedName("media_id")
    val mediaId: Int? = null,
    @SerializedName("media_type")
    val mediaType: String? = null
)