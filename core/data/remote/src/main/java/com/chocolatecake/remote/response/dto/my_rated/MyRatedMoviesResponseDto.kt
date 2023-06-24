package com.chocolatecake.remote.response.dto.my_rated


import com.google.gson.annotations.SerializedName

data class MyRatedMoviesResponseDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MyRatedMovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)