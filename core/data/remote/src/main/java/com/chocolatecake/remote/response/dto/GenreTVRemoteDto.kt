package com.chocolatecake.remote.response.dto

import com.google.gson.annotations.SerializedName

data class GenreTVRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
