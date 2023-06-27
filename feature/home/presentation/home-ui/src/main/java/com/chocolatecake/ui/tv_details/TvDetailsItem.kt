package com.chocolatecake.ui.tv_details

import com.chocolatecake.viewmodel.common.model.CommentUIState
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.common.model.SeasonHorizontalUIState
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState

sealed class TvDetailsItem(val type: TvDetailsType) {
    data class Upper(val upperUiState: TvDetailsUiState.Info) : TvDetailsItem(TvDetailsType.UPPER)
    data class People(val people: List<PeopleUIState>, val isSeasonNotEmpty: Boolean) : TvDetailsItem(TvDetailsType.PEOPLE)
    data class Season(val season: SeasonHorizontalUIState) :
        TvDetailsItem(TvDetailsType.Seasons)

    data class Recommended(val recommended: List<MediaVerticalUIState>, val isCommentNotEmpty: Boolean) :
        TvDetailsItem(TvDetailsType.RECOMMENDED)

    data class Review(val review: CommentUIState) : TvDetailsItem(TvDetailsType.REVIEWS)
}

enum class TvDetailsType { UPPER, PEOPLE, Seasons, RECOMMENDED, REVIEWS }
