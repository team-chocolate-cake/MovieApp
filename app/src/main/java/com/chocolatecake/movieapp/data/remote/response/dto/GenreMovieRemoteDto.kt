package com.chocolatecake.movieapp.data.remote.response.dto


import com.google.gson.annotations.SerializedName

data class GenreMovieRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)