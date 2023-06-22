package com.chocolatecake.remote.request

import com.google.gson.annotations.SerializedName

data class AddMediaToListRequest(
    @SerializedName("media_id")
    val mediaId:Int? = null,
)