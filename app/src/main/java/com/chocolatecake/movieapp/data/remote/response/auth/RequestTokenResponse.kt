package com.chocolatecake.movieapp.data.remote.response.auth


import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName("success")
    val isSuccess: Boolean? = null,
    @SerializedName("request_token")
    val requestToken: String? = null,
    @SerializedName("expires_at")
    val expiresAt: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,
)