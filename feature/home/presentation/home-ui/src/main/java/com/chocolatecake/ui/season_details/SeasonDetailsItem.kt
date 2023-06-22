package com.chocolatecake.ui.season_details

import com.chocolatecake.viewmodel.common.model.EpisodeHorizontalUIState
import com.chocolatecake.viewmodel.season_details.SeasonDetailsUiState

sealed class SeasonDetailsItem(val type: SeasonDetailsType){
    data class OverviewItem(val overview: String):
        SeasonDetailsItem(SeasonDetailsType.OVERVIEW)

    data class EpisodeItem(val episodeHorizontalUIState: EpisodeHorizontalUIState):
        SeasonDetailsItem(SeasonDetailsType.EPISODE)
}

enum class SeasonDetailsType{
    OVERVIEW,
    EPISODE
}
