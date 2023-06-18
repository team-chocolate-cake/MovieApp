package com.chocolatecake.remote.response.movieDetails

import com.google.gson.annotations.SerializedName

data class Recommendations(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val recommendedMovies: List<RecommendedMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)