package com.chocolatecake.remote.response.dto.episode_details


import com.google.gson.annotations.SerializedName

data class EpisodeDetailsRemoteDto(
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("crew")
    val crew: List<Any?>?,
    @SerializedName("episode_number")
    val episodeNumber: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("production_code")
    val productionCode: String?,
    @SerializedName("runtime")
    val runtime: Any?,
    @SerializedName("season_number")
    val seasonNumber: Int?,
    @SerializedName("still_path")
    val stillPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    @SerializedName("vote_count")
    val voteCount: Int?
)