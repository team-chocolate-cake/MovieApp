package com.chocolatecake.viewmodel.my_rated

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.my_rated.GetMyRatedMoviesUseCase
import com.chocolatecake.usecase.my_rated.GetMyRatedTVShowsUseCase
import com.chocolatecake.viewmodel.common.listener.MovieListener
import com.chocolatecake.viewmodel.my_rated.mappers.MyRatedMovieToMovieHorizontalUiMapper
import com.chocolatecake.viewmodel.my_rated.mappers.MyRatedTvShowToMovieHorizontalUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRatedViewModel @Inject constructor(
    private val getRatedTVShowsUseCase: GetMyRatedTVShowsUseCase,
    private val getRatedMoviesUseCase: GetMyRatedMoviesUseCase,
    private val myRatedMovieToMovieHorizontalUiMapper: MyRatedMovieToMovieHorizontalUiMapper,
    private val myRatedTvShowToMovieHorizontalUiMapper: MyRatedTvShowToMovieHorizontalUiMapper,
) : BaseViewModel<MyRatedUiState, MyRatedEvents>(MyRatedUiState()) , MyRatedListner , MovieListener {


    init {
        getData()
    }

    private fun getData() {
        when (_state.value.MyRateType) {
            MyRateType.Movies -> getMyRatedMovies()
            MyRateType.TVShows -> getMyRatedTvShow()
        }
    }

    fun getMyRatedMovies(){
        _state.update { it.copy(isLoading = true,)}
        viewModelScope.launch {
            try {
                val items = getRatedMoviesUseCase().map { pagingData ->
                    pagingData.map { movie -> myRatedMovieToMovieHorizontalUiMapper.map(movie) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        MyRateType = MyRateType.Movies,
                        MyRatedMedia = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }
    fun getMyRatedTvShow(){
        _state.update { it.copy(isLoading = true,)}
        viewModelScope.launch {
            try {
                val items = getRatedTVShowsUseCase().map { pagingData ->
                    pagingData.map { tvShow -> myRatedTvShowToMovieHorizontalUiMapper.map(tvShow) }
                }.cachedIn(viewModelScope)
                _state.update {
                    it.copy(
                        MyRateType = MyRateType.TVShows,
                        MyRatedMedia = items,
                        isLoading = false,
                        errorList = emptyList()
                    )
                }
            } catch (throwable: Throwable) {
                onError(throwable)
            }
        }
    }

    private fun onError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "No network connection"
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

    override fun onBackPressed() {
        sendEvent(MyRatedEvents.NavigateBack)
    }

    override fun onClickMovieChip() {
        sendEvent(MyRatedEvents.ShowMyRatedMoviesPressed)
    }

    override fun onClickTvShowChip() {
        sendEvent(MyRatedEvents.ShowMyRatedTvShowPressed)
    }

    override fun onClickMedia(id: Int) {
        when(_state.value.MyRateType){
            MyRateType.Movies -> sendEvent(MyRatedEvents.NavigateToMovieDetails(id))
            MyRateType.TVShows -> sendEvent(MyRatedEvents.NavigateToTVShowDetails(id))
        }
    }
}