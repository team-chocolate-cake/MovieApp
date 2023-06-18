package com.chocolatecake.viewmodel.tv_details

import com.chocolatecake.viewmodel.common.model.CommentUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.common.model.SeasonHorizontalUIState
import com.chocolatecake.viewmodel.home.RecommendedUiState

data class TvDetailsUiState(
    val info: Info = Info(),
    val cast: List<PeopleUIState> = emptyList(),
    val recommended: List<RecommendedUiState> = emptyList(),
    val seasons: List<SeasonHorizontalUIState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val reviews:List<CommentUIState> = emptyList(),
    val ratingSuccess: String = ""
) {
    data class Info(
        val backdropImageUrl: String = "",
        val name: String = "",
        val rating: Float = 0.0f,
        val description: String = "",
        val genres: List<String> = emptyList(),
        val youtubeKeyId: String = "",
    )

}
