package com.chocolatecake.remote.response.dto

import com.google.gson.annotations.SerializedName

data class MoviesByPeopleResponse(

    @SerializedName("cast")
    val cast: List<CastItem?>? = null,

    @SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("crew")
    val crew: List<CrewItem?>? = null
)

data class CrewItem(

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("credit_id")
    val creditId: String? = null,

    @SerializedName("popularity")
    val popularity: Any? = null,

    @SerializedName("vote_average")
    val voteAverage: Any? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("department")
    val department: String? = null,

    @SerializedName("job")
    val job: String? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null
)

data class CastItem(

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("character")
    val character: String? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("credit_id")
    val creditId: String? = null,

    @SerializedName("popularity")
    val popularity: Any? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null,

    @SerializedName("order")
    val order: Int? = null
)
