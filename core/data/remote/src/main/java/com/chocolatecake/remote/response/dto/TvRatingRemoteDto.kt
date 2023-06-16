package com.chocolatecake.remote.response.dto


import com.google.gson.annotations.SerializedName

data class TvRatingRemoteDto(
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?
)