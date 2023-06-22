package com.chocolatecake.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.adapter.HomeAdapter
import com.chocolatecake.ui.home.databinding.FragmentHomeBinding
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
        collectChange()
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                homeAdapter.setItems(
                    mutableListOf(
                        HomeItem.Slider(state.upComingMovies),
                        HomeItem.NowPlaying(state.nowPlayingMovies),
                        HomeItem.Trending(state.trendingMovies),
                        HomeItem.TopRated(state.topRated),
                        HomeItem.PopularPeople(state.popularPeople),
                        HomeItem.PopularMovies(state.popularMovies),
                    )
                )
                binding.recyclerViewHome.smoothScrollToPosition(0)
            }
        }
    }

    override fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.ClickShowMore -> {
                // todo: navigation
            }

            is HomeUiEvent.NowPlayingMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.PopularMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.PopularPeopleEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.RecommendedMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.TopRatedMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.TrendingMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }

            is HomeUiEvent.UpComingMovieEvent -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )
            }
        }
    }
}