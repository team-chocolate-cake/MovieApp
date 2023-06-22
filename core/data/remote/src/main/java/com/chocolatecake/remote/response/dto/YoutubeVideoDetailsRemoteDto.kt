package com.chocolatecake.remote.response.dto


import com.google.gson.annotations.SerializedName

data class YoutubeVideoDetailsRemoteDto(
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("site")
    val site: String? = null,
    @SerializedName("type")
    val type: String? = null,
)