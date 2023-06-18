package com.chocolatecake.remote.response.dto


import com.google.gson.annotations.SerializedName

data class TvReviewRemoteDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String?
) {
    data class AuthorDetails(
        @SerializedName("name")
        val name: String?,
        @SerializedName("username")
        val username: String?,
        @SerializedName("avatar_path")
        val avatarPath: String?,
        @SerializedName("rating")
        val rating: Double?
    )
}