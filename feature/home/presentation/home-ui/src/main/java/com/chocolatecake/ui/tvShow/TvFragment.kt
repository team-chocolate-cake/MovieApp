package com.chocolatecake.ui.tvShow

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chocolatecake.bases.BaseFooterAdapter
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowUIState
import com.chocolatecake.viewmodel.tv_shows.TVShowsInteraction
import com.chocolatecake.viewmodel.tv_shows.TVShowsType
import com.chocolatecake.viewmodel.tv_shows.TVShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding, TVShowUIState, TVShowsInteraction>() {

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                Log.d("state", state.toString())
                val flow = when (state.tvShowsType) {
                    TVShowsType.AIRING_TODAY -> state.tvShowAiringToday
                    TVShowsType.ON_THE_AIR -> state.tvShowOnTheAir
                    TVShowsType.TOP_RATED -> state.tvShowTopRated
                    TVShowsType.POPULAR -> state.tvShowPopular
                }
                collectLast(flow) { itemsPagingData ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        tvShowsAdapter.submitData(itemsPagingData)
                    }
                }
            }
        }
    }

    private fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(action)
            }
        }
    }

    override fun onEvent(event: TVShowsInteraction) {
        when (event) {
            is TVShowsInteraction.ShowOnTheAirTVShowsResult -> showOnTheAirResult()
            is TVShowsInteraction.ShowAiringTodayTVShowsResult -> showAiringTodayResult()
            is TVShowsInteraction.ShowTopRatedTVShowsResult -> showTopRatedResult()
            is TVShowsInteraction.ShowPopularTVShowsResult -> showPopularResult()
            is TVShowsInteraction.NavigateToTVShowDetails -> navigateToTv(event.tvId)
        }
    }

    private fun showOnTheAirResult() = viewModel.getOnTheAirTVShows()
    private fun showAiringTodayResult() = viewModel.getAiringTodayTVShows()
    private fun showTopRatedResult() = viewModel.getTopRatedTVShows()
    private fun showPopularResult() = viewModel.getPopularTVShows()
    private fun navigateToTv(tvId: Int) {
        Log.d("nav", "----------------$tvId-----------------")
        Toast.makeText(requireActivity(), tvId.toString(), Toast.LENGTH_SHORT).show()
    }

}