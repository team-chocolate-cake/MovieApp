package com.chocolatecake.viewmodel.season_details

sealed interface SeasonDetailsUiEvent {
    data class NavigateToEpisodeDetails(val episodeId: Int) : SeasonDetailsUiEvent
    data class ShowSnackBar(val messages: String) : SeasonDetailsUiEvent
    object NavigateBack: SeasonDetailsUiEvent
}