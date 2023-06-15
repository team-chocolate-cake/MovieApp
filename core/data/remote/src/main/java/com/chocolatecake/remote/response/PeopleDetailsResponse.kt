package com.chocolatecake.remote.response

import com.google.gson.annotations.SerializedName

data class PeopleDetailsResponse(

    @field:SerializedName("also_known_as")
    val alsoKnownAs: List<String?>? = null,

    @field:SerializedName("birthday")
    val birthday: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("imdb_id")
    val imdbId: String? = null,

    @field:SerializedName("known_for_department")
    val knownForDepartment: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    @field:SerializedName("biography")
    val biography: String? = null,

    @field:SerializedName("deathday")
    val deathday: Any? = null,

    @field:SerializedName("place_of_birth")
    val placeOfBirth: String? = null,

    @field:SerializedName("popularity")
    val popularity: Any? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("homepage")
    val homepage: Any? = null
)
