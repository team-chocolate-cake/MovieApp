package com.chocolatecake.viewmodel.profile.tv_shows

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TVShowUIState(
    val tvShowsType: TVShowsType = TVShowsType.AIRING_TODAY,
    val tvShowAiringToday: Flow<PagingData<TVShowsUI>> = emptyFlow(),
    val tvShowTopRated: Flow<PagingData<TVShowsUI>> = emptyFlow(),
    val tvShowOnTheAir: Flow<PagingData<TVShowsUI>> = emptyFlow(),
    val tvShowPopular: Flow<PagingData<TVShowsUI>> = emptyFlow(),
    val error: List<String>? = null,
    val isLoading: Boolean = false
)

data class TVShowsUI(
    val id: Int?,
    val imageUrl: String?,
)

enum class TVShowsType {
    AIRING_TODAY,
    ON_THE_AIR,
    TOP_RATED,
    POPULAR
}

