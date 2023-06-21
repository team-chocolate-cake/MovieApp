package com.chocolatecake.remote.response.dto

import com.google.gson.annotations.SerializedName

data class GenreTvRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)