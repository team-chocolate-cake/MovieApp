package com.chocolatecake.viewmodel.eposideDetails

import com.chocolatecake.viewmodel.common.model.PeopleUIState

data class EpisodeDetailsUiState(
    val imageUrl: String = "",
    val episodeName: String = "",
    val episodeRate: Int = 0,
    val episodeOverview: String = "",
    val episodeNumber: Int = 0,
    val seasonNumber: Int = 0,
    val cast: List<PeopleUIState> = emptyList(),
    val productionCode: String="",
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)