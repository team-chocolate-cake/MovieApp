package com.chocolatecake.remote.response.dto


import com.google.gson.annotations.SerializedName

data class TvDetailsRemoteDto(
    @SerializedName("name")
    val name: String?,
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genres")
    val genres: List<Genre?>?,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("seasons")
    val seasons: List<Season?>?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
) {
    data class Genre(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )

    data class Season(
        @SerializedName("air_date")
        val airDate: String?,
        @SerializedName("episode_count")
        val episodeCount: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("season_number")
        val seasonNumber: Int?
    )
}