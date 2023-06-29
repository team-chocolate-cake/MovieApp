package com.chocolatecake.viewmodel.showmore

import androidx.paging.PagingData
import com.chocolatecake.bases.StringsRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ShowMoreUiState(
    val showMoreTopRated: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val showMorePopularMovies: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val showMoreTrending: Flow<PagingData<ShowMoreUi>> = emptyFlow(),
    val isLoading: Boolean = false,
    val showMoreType: ShowMoreType = ShowMoreType.POPULAR_MOVIES,
    val errorList: List<String>? = null,
    private val stringsRes: StringsRes
    ) {

    val title: String
        get() = when (showMoreType) {
            ShowMoreType.POPULAR_MOVIES -> stringsRes.popularMovies
            ShowMoreType.TOP_RATED -> stringsRes.topRatedMovies
            ShowMoreType.TRENDING -> stringsRes.trending
        }
    val isError: Boolean
        get() = errorList?.isNotEmpty() ?: false
}
