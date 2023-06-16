package com.chocolatecake.entities

data class TvDetailsInfoEntity(
    val backdropImageUrl: String,
    val name: String,
    val rating: Float,
    val description: String,
    val seasons: List<Season>,
    val genres: List<GenreEntity>
) {
    data class Season(
        val number: Int,
        val episodeCount: Int,
        val airDate: String
    )
}