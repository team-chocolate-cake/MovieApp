package com.chocolatecake.remote.request


import com.google.gson.annotations.SerializedName

data class DeleteMovieRequest(
    @SerializedName("media_id")
    val mediaId: Int? = null
)