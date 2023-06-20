package com.chocolatecake.viewmodel.tv_shows

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import androidx.paging.map
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.tv_shows.GetAiringTodayTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetOnTheAirTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetPopularTVShowsUseCase
import com.chocolatecake.usecase.tv_shows.GetTopRatedTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException
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
        when (_state.value.tvShowsType) {
            TVShowsType.AIRING_TODAY -> getAiringTodayTVShows()
            TVShowsType.ON_THE_AIR -> getOnTheAirTVShows()
            TVShowsType.TOP_RATED -> getTopRatedTVShows()
            TVShowsType.POPULAR -> getPopularTVShows()
        }
    }

    fun getAiringTodayTVShows() {
        viewModelScope.launch {
            try {
                val items = getAiringTodayTVShowsUseCase().map { pagingData ->
                    pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        tvShowsType = TVShowsType.AIRING_TODAY,
                        tvShowAiringToday = items,
                        isLoading = false,
                        errorList = null
                    )
                }
                Log.d("network", _state.value.isError.toString())
                Log.d("network", _state.value.errorList.toString())

            } catch (throwable: Throwable) {
                onError(throwable)
                Log.d("network", _state.toString() + throwable.message.toString())
            }
        }
    }



    fun getOnTheAirTVShows() {
        viewModelScope.launch {
            try {
                val items = getOnTheAirTVShowsUseCase().map { pagingData ->
                    pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        tvShowsType = TVShowsType.ON_THE_AIR,
                        tvShowOnTheAir = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }

    fun getPopularTVShows() {
        viewModelScope.launch {
            try {
                val items = getPopularTVShowsUseCase().map { pagingData ->
                    pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        tvShowsType = TVShowsType.POPULAR,
                        tvShowPopular = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }

    fun getTopRatedTVShows() {
        viewModelScope.launch {
            try {
                val items = getGetTopRatedTVShowsUseCase().map { pagingData ->
                    pagingData.map { tvShow -> tvShowsMapper.map(tvShow) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        tvShowsType = TVShowsType.TOP_RATED,
                        tvShowTopRated = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }
    /// endregion

    private fun onError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "No network connection"
        showErrorWithSnackBar(errorMessage)
        _state.update {
            it.copy(
                errorList = listOf(errorMessage),
                isLoading = false
            )
        }
        Log.d("network", _state.toString())
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(TVShowsInteraction.ShowSnackBar(messages))
    }

    ///region event
    override fun onClickTVShows(tvId: Int) {
        sendEvent(TVShowsInteraction.NavigateToTVShowDetails(tvId))
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
