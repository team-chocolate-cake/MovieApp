package com.chocolatecake.remote.response.dto.profile


import com.google.gson.annotations.SerializedName

data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: Any? = null
)