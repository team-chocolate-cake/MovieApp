package com.chocolatecake.viewmodel.tv_shows

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TVShowUIState(
    val tvShowsType: TVShowsType = TVShowsType.TOP_RATED,
    val tvShowResult: Flow<PagingData<TVShowsUI>> = emptyFlow(),
    val onErrors: List<String> = emptyList(),
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

