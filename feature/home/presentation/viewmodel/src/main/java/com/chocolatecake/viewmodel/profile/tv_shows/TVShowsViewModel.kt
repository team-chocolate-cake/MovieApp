package com.chocolatecake.viewmodel.profile.tv_shows

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.usecase.GetAllGenresTvsUseCase
import com.chocolatecake.usecase.tv_shows.GetAiringTodayTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetOnTheAirTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetPopularTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetTopRatedTVShowsUseCase
import com.chocolatecake.viewmodel.search.mappers.GenreUiStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    fun getData() {
        when (_state.value.tvShowsType) {
            TVShowsType.ON_THE_AIR -> getOnTheAirTVShows()
            TVShowsType.AIRING_TODAY -> getAiringTodayTVShows()
            TVShowsType.TOP_RATED -> getTopRatedTVShows()
            TVShowsType.POPULAR -> getPopularTVShows()
        }
    }

    fun getAiringTodayTVShows() {
        viewModelScope.launch {
            val items = getAiringTodayTVShowsUseCase().map { pagingData ->
                pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
            }.cachedIn(viewModelScope)
            _state.update {
                it.copy(
                    tvShowsType = TVShowsType.AIRING_TODAY,
                    tvShowAiringToday = items,
                    isLoading = false,
                    error = emptyList()
                )
            }
            Log.d("chips-----ViewModel", "AiringToday---- ${items.collect()} ")
        }
    }

    fun getOnTheAirTVShows() {
        viewModelScope.launch {
            val items = getOnTheAirTVShowsUseCase().map { pagingData ->
                pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
            }.cachedIn(viewModelScope)
            _state.update {
                it.copy(
                    tvShowsType = TVShowsType.ON_THE_AIR,
                    tvShowOnTheAir = items,
                    isLoading = false,
                    error = emptyList()
                )
            }
            Log.d("chips-----ViewModel", "OnTheAir---- $items ")
        }
    }

    fun getPopularTVShows() {
        viewModelScope.launch {
            val items = getPopularTVShowsUseCase().map { pagingData ->
                pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
            }.cachedIn(viewModelScope)
            _state.update {
                it.copy(
                    tvShowsType = TVShowsType.POPULAR,
                    tvShowPopular = items,
                    isLoading = false,
                    error = emptyList()
                )
            }
            Log.d("chips-----ViewModel", "Popular---- $items ")
        }
    }

    fun getTopRatedTVShows() {
        viewModelScope.launch {
            val items = getGetTopRatedTVShowsUseCase().map { pagingData ->
                pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
            }.cachedIn(viewModelScope)
            _state.update {
                it.copy(
                    tvShowsType = TVShowsType.TOP_RATED,
                    tvShowTopRated = items,
                    isLoading = false,
                    error = emptyList()
                )
            }
            Log.d("chips-----ViewModel", "TopRated---- $items ")
        }
    }



    ///region event
    override fun onClickTVShows(itemId: Int) {
        sendEvent(TVShowsInteraction.NavigateToTVShowDetails(itemId))
    }

    override fun showOnTheAiringTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowOnTheAirTVShowsResult)
    }

    override fun showAiringTodayTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowAiringTodayTVShowsResult)
    }

    override fun showTopRatedTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowTopRatedTVShowsResult)
    }

    override fun showPopularTVShowsResult() {
        sendEvent(TVShowsInteraction.ShowPopularTVShowsResult)
    }
    /// endregion
}