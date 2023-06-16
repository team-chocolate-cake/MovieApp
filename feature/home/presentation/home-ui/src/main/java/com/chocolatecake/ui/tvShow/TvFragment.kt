package com.chocolatecake.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.bases.BaseFooterAdapter
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowUIState
import com.chocolatecake.viewmodel.tv_shows.TVShowsInteraction
import com.chocolatecake.viewmodel.tv_shows.TVShowsUI
import com.chocolatecake.viewmodel.tv_shows.TVShowsViewModel
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
        binding.rvTvShows.adapter = tvShowsAdapter.withLoadStateFooter(footerAdapter)

        collect(flow = tvShowsAdapter.loadStateFlow,
            action = { viewModel.state.value.onErrors })

        collectLast(viewModel.state.value.tvShowResult, ::setAllTVShows)

        getTVShowsResults()
    }
    private fun getTVShowsResults() {
        collectLast(viewModel.state.value.tvShowResult)
        { tvShowsAdapter.submitData(it) }
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
        }
    }

    private fun showOnTheAirResult() {
        viewModel.getOnTheAirTVShows()
        Log.d("chips-----Fragment", "OnTheAirTVShows")
    }

    private fun showAiringTodayResult() {
            viewModel.getAiringTodayTVShows()
            Log.d("chips-----Fragment", "AiringToday")
    }

    private fun showTopRatedResult() {
            viewModel.getTopRatedTVShows()
            Log.d("chips-----Fragment", "TopRated")
    }

    private fun showPopularResult() {
            viewModel.getPopularTVShows()
            Log.d("chips-----Fragment", "Popular")
    }
}