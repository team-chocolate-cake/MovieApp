package com.chocolatecake.remote.response.dto

import com.google.gson.annotations.SerializedName

data class PeopleDetailsResponse(

    @SerializedName("also_known_as")
    val alsoKnownAs: List<String?>? = null,

    @SerializedName("birthday")
    val birthday: String? = null,

    @SerializedName("gender")
    val gender: Int? = null,

    @SerializedName("imdb_id")
    val imdbId: String? = null,

    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("biography")
    val biography: String? = null,

    @SerializedName("deathday")
    val deathday: Any? = null,

    @SerializedName("place_of_birth")
    val placeOfBirth: String? = null,

    @SerializedName("popularity")
    val popularity: Any? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("homepage")
    val homepage: Any? = null
)
