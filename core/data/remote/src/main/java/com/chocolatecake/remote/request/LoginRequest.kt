package com.chocolatecake.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String
)
