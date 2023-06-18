package com.chocolatecake.ui.tv_details

import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.common.model.SeasonHorizontalUIState
import com.chocolatecake.viewmodel.home.RecommendedUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState

sealed class TvDetailsItem(val type: TvDetailsType) {
    data class Upper(val upperUiState: TvDetailsUiState.Info) : TvDetailsItem(TvDetailsType.UPPER)
    data class People(val people: List<PeopleUIState>) : TvDetailsItem(TvDetailsType.PEOPLE)
    data class Season(val season: SeasonHorizontalUIState) :
        TvDetailsItem(TvDetailsType.Seasons)

    data class Recommended(val recommended: List<RecommendedUiState>) :
        TvDetailsItem(TvDetailsType.RECOMMENDED)
}

enum class TvDetailsType { UPPER, PEOPLE, Seasons, RECOMMENDED, REVIEWS }
