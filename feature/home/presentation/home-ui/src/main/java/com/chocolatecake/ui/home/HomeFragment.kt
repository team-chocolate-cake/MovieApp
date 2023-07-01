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
import com.chocolatecake.viewmodel.showmore.ShowMoreType
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

            is HomeUiEvent.MovieEvent -> {
                try {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
                            event.itemId
                        )
                    )
                }catch (_:Exception){ }
            }

            HomeUiEvent.ClickShowMore -> {
                try {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToShowMoreFragment(
                            showMoreType = ShowMoreType.POPULAR_MOVIES
                        )
                    )
                }catch (_:Exception){ }
            }
        }
    }
}