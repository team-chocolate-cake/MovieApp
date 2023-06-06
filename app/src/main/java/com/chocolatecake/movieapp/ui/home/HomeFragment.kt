package com.chocolatecake.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentHomeBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment
import com.chocolatecake.movieapp.ui.home.adapter.HomeAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.HomeUiEvent
import com.chocolatecake.movieapp.ui.home.ui_state.HomeUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeUiState, HomeUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    override fun onSateChange(state: HomeUiState) {
        homeAdapter.setItems(
            mutableListOf(
                state.upComingMovies,
                state.nowPlayingMovies,
                state.trendingMovies,
                state.topRated,
                state.popularPeople,
                state.popularMovies,
            )
        )
        binding.recyclerViewHome.smoothScrollToPosition(0)
    }

    override fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.ClickShowMore -> {
                // todo: navigation
            }

            is HomeUiEvent.NowPlayingMovieEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.PopularMovieEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.PopularPeopleEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.RecommendedMovieEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.TopRatedMovieEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.TrendingMovieEvent -> {
                // todo: navigation
            }

            is HomeUiEvent.UpComingMovieEvent -> {
                // todo: navigation
            }
        }
    }


}