package com.chocolatecake.movieapp.data.remote.response.dto


import com.google.gson.annotations.SerializedName

data class PeopleRemoteDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)