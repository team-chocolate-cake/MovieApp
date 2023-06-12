package com.chocolatecake.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.R
import com.chocolatecake.ui.databinding.FragmentHomeBinding
import com.chocolatecake.ui.home.adapter.HomeAdapter
import com.chocolatecake.viewmodel.home.HomeUiEvent
import com.chocolatecake.viewmodel.home.HomeUiState
import com.chocolatecake.viewmodel.home.HomeViewModel
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