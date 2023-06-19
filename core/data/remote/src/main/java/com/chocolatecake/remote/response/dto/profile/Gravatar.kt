package com.chocolatecake.remote.response.dto.profile


import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName("hash")
    val hash: String? = null
)