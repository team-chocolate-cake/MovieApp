package com.chocolatecake.remote.request

import com.google.gson.annotations.SerializedName

data class RateRequest(
    @SerializedName("value")
    val value:Double
)
