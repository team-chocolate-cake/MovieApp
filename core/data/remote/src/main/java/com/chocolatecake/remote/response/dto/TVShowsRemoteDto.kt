package com.chocolatecake.remote.response.dto


import com.google.gson.annotations.SerializedName

data class TVShowsRemoteDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)