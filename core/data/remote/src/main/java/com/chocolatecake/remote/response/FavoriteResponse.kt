package com.chocolatecake.remote.response


import com.google.gson.annotations.SerializedName

data class FavoriteResponse(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
)