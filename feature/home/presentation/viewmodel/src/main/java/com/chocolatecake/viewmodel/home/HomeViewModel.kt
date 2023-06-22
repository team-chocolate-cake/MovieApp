package com.chocolatecake.viewmodel.home

import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.home.GetNowPlayingUseCase
import com.chocolatecake.usecase.home.GetPopularMoviesUseCase
import com.chocolatecake.usecase.home.GetPopularPeopleUseCase
import com.chocolatecake.usecase.home.GetTopRatedUseCase
import com.chocolatecake.usecase.home.GetTrendingMoviesUseCase
import com.chocolatecake.usecase.home.GetUpcomingMoviesUseCase
import com.chocolatecake.viewmodel.home.mappers.NowPlayingUiMapper
import com.chocolatecake.viewmodel.home.mappers.PopularMoviesUiMapper
import com.chocolatecake.viewmodel.home.mappers.PopularPeopleUiMapper
import com.chocolatecake.viewmodel.home.mappers.TopRatedUiMapper
import com.chocolatecake.viewmodel.home.mappers.TrendingUiMapper
import com.chocolatecake.viewmodel.home.mappers.UpComingUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val nowPlayingUseCase: GetNowPlayingUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularPeopleUseCase: GetPopularPeopleUseCase,
    private val topRatedUseCase: GetTopRatedUseCase,
    private val trendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val upComingUiMapper: UpComingUiMapper,
    private val nowPlayingUiMapper: NowPlayingUiMapper,
    private val trendingUiMapper: TrendingUiMapper,
    private val topRatedUiMapper: TopRatedUiMapper,
    private val popularPeopleUiMapper: PopularPeopleUiMapper,
    private val popularMoviesUiMapper: PopularMoviesUiMapper,
) : BaseViewModel<HomeUiState, HomeUiEvent>(HomeUiState()), HomeListener {


    /// region init
    init {
        getData()
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true) }
        getUpComingMovies()
        getPopularPeople()
        getNowPlayingMovies()
        getTrendingMovies()
        getPopularMovies()
        getTopRatedMovies()

    }
    /// endregion

    /// region call
    private fun getPopularMovies() {
        tryToExecute(
            call = { popularMoviesUseCase() },
            onSuccess = ::onSuccessPopularMovies,
            mapper = popularMoviesUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessPopularMovies(popularMovieEntities: List<PopularMoviesUiState>) {
        _state.update {
            it.copy(
                popularMovies = popularMovieEntities,
                isLoading = false
            )
        }
    }

    private fun getTopRatedMovies() {
        tryToExecute(
            call = { topRatedUseCase() },
            onSuccess = ::onSuccessTopRatedMovies,
            mapper = topRatedUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessTopRatedMovies(topRatedMovieEntities: List<TopRatedUiState>) {
        _state.update {
            it.copy(
                topRated = topRatedMovieEntities,
                isLoading = false
            )
        }
    }


    private fun getUpComingMovies() {
        tryToExecute(
            call = { upcomingMoviesUseCase() },
            onSuccess = ::onSuccessUpcomingMovies,
            mapper = upComingUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessUpcomingMovies(upcomingMovieEntities: List<UpComingMoviesUiState>) {
        _state.update {
            it.copy(
                upComingMovies = upcomingMovieEntities,
                isLoading = false
            )
        }
    }

    private fun getPopularPeople() {
        tryToExecute(
            call = { popularPeopleUseCase() },
            onSuccess = ::onSuccessPopularPeople,
            mapper = popularPeopleUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessPopularPeople(popularPeopleEntities: List<PopularPeopleUiState>) {
        _state.update {
            it.copy(
                popularPeople = popularPeopleEntities,
                isLoading = false
            )
        }
    }

    private fun getNowPlayingMovies() {
        tryToExecute(
            call = { nowPlayingUseCase() },
            onSuccess = ::onSuccessNowPlayingMovies,
            mapper = nowPlayingUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessNowPlayingMovies(nowPlayingMovieEntities: List<NowPlayingUiState>) {
        _state.update {
            it.copy(
                nowPlayingMovies = nowPlayingMovieEntities,
                isLoading = false
            )
        }
    }

    private fun getTrendingMovies() {
        tryToExecute(
            call = { trendingMoviesUseCase() },
            onSuccess = ::onSuccessTrendingMovies,
            mapper = trendingUiMapper,
            onError = ::onError
        )
    }

    private fun onSuccessTrendingMovies(trendingMoviesEntities: List<TrendingMoviesUiState>) {
        _state.update {
            it.copy(
                trendingMovies = trendingMoviesEntities,
                isLoading = false
            )
        }
    }
    /// endregion

    /// region errors
    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }
    /// endregion

    /// region events
    override fun onClickNowPlaying(itemId: Int) {
        sendEvent(HomeUiEvent.NowPlayingMovieEvent(itemId))
    }

    override fun onClickTrending(itemId: Int) {
        sendEvent(HomeUiEvent.TrendingMovieEvent(itemId))
    }

    override fun onClickPopularMovies(itemId: Int) {
        sendEvent(HomeUiEvent.PopularMovieEvent(itemId))
    }

    override fun onClickTopRated(itemId: Int) {
        sendEvent(HomeUiEvent.TopRatedMovieEvent(itemId))
    }


    override fun onClickUpComing(itemId: Int) {
        sendEvent(HomeUiEvent.UpComingMovieEvent(itemId))
    }

    override fun onClickPopularPeople(itemId: Int) {
        sendEvent(HomeUiEvent.PopularPeopleEvent(itemId))
    }
    /// endregion

}