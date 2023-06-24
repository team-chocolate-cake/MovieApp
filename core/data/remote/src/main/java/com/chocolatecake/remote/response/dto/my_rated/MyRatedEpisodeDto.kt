package com.chocolatecake.remote.response.dto.my_rated


import com.google.gson.annotations.SerializedName

data class MyRatedEpisodeDto(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode_number")
    val episodeNumber: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("production_code")
    val productionCode: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("show_id")
    val showId: Int,
    @SerializedName("still_path")
    val stillPath: Any,
    @SerializedName("vote_average")
    val voteAverage: Int,
    @SerializedName("vote_count")
    val voteCount: Int
)