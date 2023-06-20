package com.chocolatecake.remote.response.dto.episode_details

import com.google.gson.annotations.SerializedName

data class RatingEpisodeDetailsRemoteDto(
    @SerializedName("average_rating")
    val averageRating: Int,
    @SerializedName("vote_count")
    val voteCount: Int
)
