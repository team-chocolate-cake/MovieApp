package com.chocolatecake.movieapp.home

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.usecases.home.GetNowPlayingUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetPopularMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetPopularPeopleUsecase
import com.chocolatecake.movieapp.domain.usecases.home.GetRecommendedUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetTopRatedUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetTrendingMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetUpcomingMoviesUseCase
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import com.chocolatecake.movieapp.ui.home.ui_state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    val nowPlayingUseCase: GetNowPlayingUseCase,
    val popularMoviesUseCase: GetPopularMoviesUseCase,
    val popularPeopleUseCase: GetPopularPeopleUsecase,
    val recommendedUseCase: GetRecommendedUseCase,
    val topRatedUseCase: GetTopRatedUseCase,
    val trendingMoviesUseCase: GetTrendingMoviesUseCase,
    upcomingMoviesUseCase: GetUpcomingMoviesUseCase
) :
    BaseViewModel(), HomeListener {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    override fun getData() {


    }

    override fun onClickNowPlaying(itemId: Int) {

    }

    override fun onClickTrending(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularMovies(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickTopRated(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickRecommended(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickUpComing(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularPeople(itemId: Int) {
        TODO("Not yet implemented")
    }

}