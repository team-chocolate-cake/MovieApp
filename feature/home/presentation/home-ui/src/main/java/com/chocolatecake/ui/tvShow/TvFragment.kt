package com.chocolatecake.ui.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.chocolatecake.bases.BaseFooterAdapter
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvBinding
import com.chocolatecake.viewmodel.profile.tv_shows.TVShowUIState
import com.chocolatecake.viewmodel.profile.tv_shows.TVShowsInteraction
import com.chocolatecake.viewmodel.profile.tv_shows.TVShowsType
import com.chocolatecake.viewmodel.profile.tv_shows.TVShowsUI
import com.chocolatecake.viewmodel.profile.tv_shows.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvFragment :
    BaseFragment<FragmentTvBinding, TVShowUIState, TVShowsInteraction>() {

    override val layoutIdFragment = R.layout.fragment_tv
    override val viewModel: TVShowsViewModel by viewModels()
    private val tvShowsAdapter by lazy { TVShowsAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val footerAdapter = BaseFooterAdapter { tvShowsAdapter.retry() }
        binding.recyclerViewTvShows.adapter = tvShowsAdapter.withLoadStateFooter(footerAdapter)


        collect(flow = tvShowsAdapter.loadStateFlow,
            action = { viewModel.state.value.error })

        collectLatest {
            viewModel.state.collectLatest {
                when (it.tvShowsType) {
                    TVShowsType.AIRING_TODAY -> collectLast(
                        viewModel.state.value.tvShowAiringToday,
                        ::setAllTVShows
                    )

                    TVShowsType.ON_THE_AIR -> collectLast(
                        viewModel.state.value.tvShowOnTheAir,
                        ::setAllTVShows
                    )

                    TVShowsType.TOP_RATED -> collectLast(
                        viewModel.state.value.tvShowTopRated,
                        ::setAllTVShows
                    )

                    TVShowsType.POPULAR -> collectLast(
                        viewModel.state.value.tvShowPopular,
                        ::setAllTVShows
                    )
                }
            }
        }
    }

    private suspend fun setAllTVShows(itemsPagingData: PagingData<TVShowsUI>) {
        viewLifecycleOwner.lifecycleScope.launch {
            tvShowsAdapter.submitData(itemsPagingData)
        }
    }

    fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(action)
            }
        }
    }

    fun <T> LifecycleOwner.collect(flow: Flow<T>, action: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    action.invoke(it)
                }
            }
        }
    }

    override fun onEvent(event: TVShowsInteraction) {
        when (event) {
            is TVShowsInteraction.ShowOnTheAirTVShowsResult -> showOnTheAirResult()
            is TVShowsInteraction.ShowAiringTodayTVShowsResult -> showAiringTodayResult()
            is TVShowsInteraction.ShowTopRatedTVShowsResult -> showTopRatedResult()
            is TVShowsInteraction.ShowPopularTVShowsResult -> showPopularResult()
            is TVShowsInteraction.NavigateToTVShowDetails -> TODO()
            else -> {}
        }
    }

    private fun showOnTheAirResult() {
          viewModel.getOnTheAirTVShows()
    }

    private fun showAiringTodayResult() {
           viewModel.getAiringTodayTVShows()
    }

    private fun showTopRatedResult() {
          viewModel.getTopRatedTVShows()
    }

    private fun showPopularResult() {
           viewModel.getPopularTVShows()
    }
}