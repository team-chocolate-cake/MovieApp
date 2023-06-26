package com.chocolatecake.remote.response.dto
import com.google.gson.annotations.SerializedName

data class TvShowsByPeopleResponse(

    @field:SerializedName("cast")
    val cast: List<TvShowsCastItem?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("crew")
    val crew: List<Any?>? = null
)

data class TvShowsCastItem(

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("episode_count")
    val episodeCount: Int? = null,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("origin_country")
    val originCountry: List<String?>? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("character")
    val character: String? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("popularity")
    val popularity: Any? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("vote_count")
    val voteCount: Int? = null
)
