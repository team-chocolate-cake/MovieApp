package com.chocolatecake.viewmodel.showmore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.usecase.showmore.GetMorePopularMoviesByTypeUseCase
import com.chocolatecake.usecase.showmore.GetMoreTopRatedByTypeUseCase
import com.chocolatecake.usecase.showmore.GetMoreTrendingByTypeUseCase
import com.chocolatecake.viewmodel.showmore.mappers.ShowMoreUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ShowMoreViewModel @Inject constructor(
    private val getShowMorePopularMoviesByTypeUseCase: GetMorePopularMoviesByTypeUseCase,
    private val getShowMoreTopRatedByTypeUseCase: GetMoreTopRatedByTypeUseCase,
    private val getShowMoreTrendingByTypeUseCase: GetMoreTrendingByTypeUseCase,
    private val showMoreUiMapper: ShowMoreUiMapper,
    savedStateHandle: SavedStateHandle,
    stringsRes: StringsRes
) : BaseViewModel<ShowMoreUiState, ShowMoreUiEvent>(
    ShowMoreUiState(
        showMoreType = savedStateHandle.get<ShowMoreType>(
            "showMoreType"
        ) ?: ShowMoreType.POPULAR_MOVIES
    ,
        stringsRes = stringsRes
    )
), ShowMoreListener {

        init {
            _state.update { it.copy(isLoading = true) }
            getData()
        }

    fun getData() {
        when (_state.value.showMoreType) {
            ShowMoreType.POPULAR_MOVIES -> getPopularMoviesShowMore()
            ShowMoreType.TOP_RATED -> getTopRatedShowMore()
            ShowMoreType.TRENDING -> getTrendingShowMore()
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
                    it.copy(isLoading = false, errorList = listOf("no Network "))
                }
            }
        }
    }

    private fun getPopularMoviesShowMore() {
        viewModelScope.launch {
            try {
                val items = getShowMorePopularMoviesByTypeUseCase().map { pagingData ->
                    pagingData.map { Showmore -> showMoreUiMapper.map(Showmore) }

                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        showMoreType = ShowMoreType.POPULAR_MOVIES,
                        showMorePopularMovies = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: UnknownHostException) {
                onError(throwable)
            }
        }
    }

    private fun getTopRatedShowMore() {
        viewModelScope.launch {
            try {

                val items = getShowMoreTopRatedByTypeUseCase().map { pagingData ->
                    pagingData.map { Showmore -> showMoreUiMapper.map(Showmore) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        showMoreType = ShowMoreType.TOP_RATED,
                        showMoreTopRated = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: UnknownHostException) {
                onError(throwable)
            }
        }
    }

    private fun getTrendingShowMore() {
        viewModelScope.launch {
            try {

                val items = getShowMoreTrendingByTypeUseCase().map { pagingData ->
                    pagingData.map { Showmore -> showMoreUiMapper.map(Showmore) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        showMoreType = ShowMoreType.TRENDING,
                        showMoreTrending = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }

            } catch (throwable: UnknownHostException) {
                onError(throwable)
            }
        }
    }

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

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(ShowMoreUiEvent.ShowSnackBar(messages))
    }

    override fun backNavigate() {
        sendEvent(ShowMoreUiEvent.BackNavigate)
    }

    override fun onClickMedia(mediaId: Int) {
        sendEvent(ShowMoreUiEvent.NavigateToMovieDetails(mediaId))
    }
}
