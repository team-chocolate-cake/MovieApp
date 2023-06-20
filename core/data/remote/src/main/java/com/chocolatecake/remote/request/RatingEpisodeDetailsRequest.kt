package com.chocolatecake.remote.request

import com.google.gson.annotations.SerializedName

data class RatingEpisodeDetailsRequest(
    @SerializedName("value")
    val value: Int
)
