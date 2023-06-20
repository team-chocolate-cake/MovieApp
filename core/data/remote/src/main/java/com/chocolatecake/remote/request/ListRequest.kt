package com.chocolatecake.remote.request


import com.google.gson.annotations.SerializedName

data class ListRequest(
//    @SerializedName("description")
//    val description: String? = null,
//    @SerializedName("language")
//    val language: String? = null,
    @SerializedName("name")
    val name: String? = null
)