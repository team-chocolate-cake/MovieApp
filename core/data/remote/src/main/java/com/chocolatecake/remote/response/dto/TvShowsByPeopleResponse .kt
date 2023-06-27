package com.chocolatecake.remote.response.dto
import com.google.gson.annotations.SerializedName

data class TvShowsByPeopleResponse(

    @SerializedName("cast")
    val cast: List<TvShowsCastItem?>? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("crew")
    val crew: List<Any?>? = null
)

data class TvShowsCastItem(

    @SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("episode_count")
    val episodeCount: Int? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("character")
    val character: String? = null,

    @SerializedName("credit_id")
    val creditId: String? = null,

    @SerializedName("original_name")
    val originalName: String? = null,

    @SerializedName("popularity")
    val popularity: Any? = null,

    @SerializedName("vote_average")
    val voteAverage: Any? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null
)
