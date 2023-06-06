package com.chocolatecake.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class GenreMovieDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)