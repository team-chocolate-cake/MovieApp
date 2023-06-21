package com.chocolatecake.viewmodel.showmore

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ShowMoreUiState(
    val showMoreTopRated: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val showMorePopularMovies: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val showMoreTrending: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val isLoading: Boolean = false,
    val showMoreType: ShowMoreType = ShowMoreType.POPULAR_MOVIES,
    val errorList: List<String>? = null,

    ) {

    val title: String
        get() = when (showMoreType) {
            ShowMoreType.POPULAR_MOVIES -> "Popular Movies"
            ShowMoreType.TOP_RATED -> "Top Rated Movies"
            ShowMoreType.TRENDING -> "Trending Movies"
        }
    val isError: Boolean
        get() = errorList?.isNotEmpty() ?: false
}
