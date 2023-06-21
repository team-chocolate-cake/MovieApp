package com.chocolatecake.remote.response.dto.season_details

import com.google.gson.annotations.SerializedName

data class SeasonDetailsDto(
    @SerializedName("air_date")
    val airDate: String? = "",
    @SerializedName("episodes")
    val episodes: List<EpisodeDto>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("season_number")
    val seasonNumber: Int? = 0
)
