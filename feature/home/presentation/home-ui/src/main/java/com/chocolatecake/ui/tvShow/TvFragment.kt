package com.chocolatecake.ui.tvShow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chocolatecake.bases.BaseFooterAdapter
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvBinding
import com.chocolatecake.viewmodel.tv_shows.TVShowUIState
import com.chocolatecake.viewmodel.tv_shows.TVShowsInteraction
import com.chocolatecake.viewmodel.tv_shows.TVShowsType
import com.chocolatecake.viewmodel.tv_shows.TVShowsViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding, TVShowUIState, TVShowsInteraction>() {

    override val layoutIdFragment = R.layout.fragment_tv
    override val viewModel: TVShowsViewModel by viewModels()
    private val tvShowsAdapter by lazy { TVShowsAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        doNothingWhenTheSameChipIsReselected()
    }

    private fun setAdapter() {
        val footerAdapter = BaseFooterAdapter { tvShowsAdapter.retry() }
        binding.recyclerViewTvShows.adapter = tvShowsAdapter.withLoadStateFooter(footerAdapter)

        val mManager = binding.recyclerViewTvShows.layoutManager as GridLayoutManager
        mManager.setSpanSize(footerAdapter, tvShowsAdapter, mManager.spanCount)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
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
                collectLast(tvShowsAdapter.loadStateFlow) { viewModel.setErrorUiState(it) }
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
            is TVShowsInteraction.ShowSnackBar -> showSnackBar(event.messages)
        }
    }

    private fun showOnTheAirResult() = viewModel.getOnTheAirTVShows()
    private fun showAiringTodayResult() = viewModel.getAiringTodayTVShows()
    private fun showTopRatedResult() = viewModel.getTopRatedTVShows()
    private fun showPopularResult() = viewModel.getPopularTVShows()
    private fun navigateToTv(tvId: Int) {
        findNavController().navigate(TvFragmentDirections.actionTvFragmentToTvDetailsFragment(tvId))
    }

    private fun doNothingWhenTheSameChipIsReselected() {
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            if (chip?.isChecked == true) {
                // Do nothing when the same chip is reselected
            }
        }
    }
}