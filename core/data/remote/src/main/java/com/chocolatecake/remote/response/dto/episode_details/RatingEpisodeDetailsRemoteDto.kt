package com.chocolatecake.remote.response.dto.episode_details

import com.google.gson.annotations.SerializedName

data class RatingEpisodeDetailsRemoteDto(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)
