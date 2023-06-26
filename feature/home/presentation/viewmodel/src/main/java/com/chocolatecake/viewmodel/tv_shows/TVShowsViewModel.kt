package com.chocolatecake.viewmodel.tv_shows

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.usecase.tv_shows.GetAiringTodayTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetOnTheAirTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetPopularTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetTopRatedTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val getAiringTodayTVShowsUseCase: GetAiringTodayTVShowsUseCase,
    private val getOnTheAirTVShowsUseCase: GetOnTheAirTVShowsUseCase,
    private val getPopularTVShowsUseCase: GetPopularTVShowsUseCase,
    private val getGetTopRatedTVShowsUseCase: GetTopRatedTVShowsUseCase,
    private val tvShowsMapper: TVShowsMapper
) : BaseViewModel<TVShowUIState, TVShowsInteraction>(TVShowUIState()), TVShowsListener {


    init {
        getData()
    }

    ///region get data
    private fun getData() {
        try {
            viewModelScope.launch {
                when (_state.value.tvShowsType) {
                    TVShowsType.AIRING_TODAY -> getAiringTodayTVShows()
                    TVShowsType.ON_THE_AIR -> getOnTheAirTVShows()
                    TVShowsType.TOP_RATED -> getTopRatedTVShows()
                    TVShowsType.POPULAR -> getPopularTVShows()
                }
            }
        } catch (throwable: Throwable) {
            onError(throwable)
        }


    }

    fun getPopularTVShows() {
        viewModelScope.launch { fetchTvShow(TVShowsType.POPULAR) { getPopularTVShowsUseCase() } }
    }

    fun getTopRatedTVShows() {
        viewModelScope.launch {
            fetchTvShow(TVShowsType.TOP_RATED) { getGetTopRatedTVShowsUseCase() }
        }
    }

    fun getOnTheAirTVShows() {
        viewModelScope.launch {
            fetchTvShow(TVShowsType.ON_THE_AIR) { getOnTheAirTVShowsUseCase() }
        }
    }

    fun getAiringTodayTVShows() {
        viewModelScope.launch {
            fetchTvShow(TVShowsType.AIRING_TODAY) { getAiringTodayTVShowsUseCase() }
        }
    }

    private suspend fun fetchTvShow(
        type: TVShowsType,
        useCase: suspend () -> Flow<PagingData<TVShowsEntity>>,
    ) {
        try {
            val items = mapItemsToUIState(useCase)
            _state.update { uiState ->
                when (type) {
                    TVShowsType.ON_THE_AIR -> updateOnTheAir(items)
                    TVShowsType.POPULAR -> updatePopular(items)
                    TVShowsType.TOP_RATED -> updateTopRated(items)
                    TVShowsType.AIRING_TODAY -> updateAiringToday(items)
                }
                uiState.copy(
                    isLoading = false,
                    errorList = emptyList()
                )
            }
        } catch (throwable: Throwable) {
            onError(throwable)
        }
    }

    private suspend fun mapItemsToUIState(
        useCase: suspend () -> Flow<PagingData<TVShowsEntity>>
    ): Flow<PagingData<TVShowsUI>> {
        return useCase().map { pagingData ->
            pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
        }.cachedIn(viewModelScope)
    }

    private fun updateAiringToday(items: Flow<PagingData<TVShowsUI>>) {
        _state.update {
            it.copy(
                tvShowsType = TVShowsType.AIRING_TODAY,
                tvShowAiringToday = items,
            )
        }
    }

    private fun updateTopRated(items: Flow<PagingData<TVShowsUI>>) {
        _state.update {
            it.copy(
                tvShowsType = TVShowsType.TOP_RATED,
                tvShowTopRated = items,
            )
        }
    }

    private fun updatePopular(items: Flow<PagingData<TVShowsUI>>) {
        _state.update {
            it.copy(
                tvShowsType = TVShowsType.POPULAR,
                tvShowPopular = items,
            )
        }
    }

    private fun updateOnTheAir(items: Flow<PagingData<TVShowsUI>>) {
        _state.update {
            it.copy(
                tvShowsType = TVShowsType.ON_THE_AIR,
                tvShowOnTheAir = items,
            )
        }
    }

    /// endregion

    ///region error
    private fun onError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "No network connection"
        showErrorWithSnackBar(errorMessage)
        _state.update {
            it.copy(
                errorList = listOf(errorMessage),
                isLoading = false
            )
        }
    }

    fun setErrorUiState(combinedLoadStates: CombinedLoadStates) {
        when (combinedLoadStates.refresh) {
            is LoadState.NotLoading -> {
                _state.update {
                    it.copy(isLoading = false, errorList = emptyList())
                }
            }

            LoadState.Loading -> {
                _state.update {
                    it.copy(isLoading = true, errorList = emptyList())
                }
            }

            is LoadState.Error -> {
                _state.update {
                    it.copy(isLoading = false, errorList = listOf("no Network"))
                }
            }
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(TVShowsInteraction.ShowSnackBar(messages))
    }
    /// endregion

    ///region event
    override fun onClickTVShows(tvId: Int) {
        sendEvent(TVShowsInteraction.NavigateToTVShowDetails(tvId))
    }

    override fun scrollToTopScreen() {
        sendEvent(TVShowsInteraction.ScrollToTopRecycler)
    }

    override fun showAiringTodayTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowAiringTodayTVShowsResult)
    }

    override fun showOnTheAiringTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowOnTheAirTVShowsResult)
    }

    override fun showTopRatedTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowTopRatedTVShowsResult)
    }

    override fun showPopularTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowPopularTVShowsResult)
    }
    /// endregion
}
