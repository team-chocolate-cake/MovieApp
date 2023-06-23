package com.chocolatecake.remote.request


import com.google.gson.annotations.SerializedName

data class ListRequest(
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("language")
    val language: String? = "en",
    @SerializedName("name")
    val name: String? = null
)