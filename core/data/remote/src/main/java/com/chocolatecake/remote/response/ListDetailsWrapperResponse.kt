package com.chocolatecake.remote.response

import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.google.gson.annotations.SerializedName

data class ListDetailsWrapperResponse<T>(
    @SerializedName("created_by")
    val createdBy: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("favorite_count")
    val favoriteCount: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("item_count")
    val itemCount: Int? = null,
    @SerializedName("items")
    val items: List<T>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: Any? = null
)