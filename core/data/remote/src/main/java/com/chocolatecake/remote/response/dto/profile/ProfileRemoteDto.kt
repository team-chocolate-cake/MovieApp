package com.chocolatecake.remote.response.dto.profile


import com.google.gson.annotations.SerializedName

data class ProfileRemoteDto(
    @SerializedName("avatar_path")
    val avatar: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("username")
    val username: String? = null
)