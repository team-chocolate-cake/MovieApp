package com.chocolatecake.remote.response.dto

import com.google.gson.annotations.SerializedName

data class ListRemoteDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("favorite_count")
    val favoriteCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("item_count")
    val itemCount: Int? = null,
    @SerializedName("list_type")
    val listType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: Any? = null,
)