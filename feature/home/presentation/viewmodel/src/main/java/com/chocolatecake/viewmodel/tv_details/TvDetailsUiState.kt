package com.chocolatecake.viewmodel.tv_details

import com.chocolatecake.viewmodel.common.model.PeopleUIState

data class TvDetailsUiState(
    val backdropImageUrl: String = "",
    val name: String = "",
    val rating: Float = 0.0f,
    val description: String = "",
    val youtubeKeyId: String = "",
    val cast: List<PeopleUIState> = emptyList(),
    val recommended: List<RecommendedTvUiState> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val genres: List<String> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val ratingSuccess: String = ""
) {
    data class RecommendedTvUiState(
        val id: Int,
        val imageUrl: String,
        val rate: Double
    )

    data class Season(
        val number: Int,
        val episodeCount: Int,
        val airDate: String
    )
}
