package com.chocolatecake.movieapp.ui.tv_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvDetailsBinding
import com.chocolatecake.ui.tv_details.RateBottomSheet
import com.chocolatecake.ui.tv_details.TvDetailsItem
import com.chocolatecake.ui.tv_details.adapter.TvDetailsAdapter
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiEvent
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailsFragment :
    BaseFragment<FragmentTvDetailsBinding, TvDetailsUiState, TvDetailsUiEvent>() {

    private lateinit var tvDetailsAdapter: TvDetailsAdapter
    override val layoutIdFragment: Int = R.layout.fragment_tv_details
    override val viewModel: TvDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        collectChange()
    }

    override fun onEvent(event: TvDetailsUiEvent) {
        when (event) {
            is TvDetailsUiEvent.Rate -> showBottomSheet()
            else -> {}
        }
    }


    private fun setAdapter() {
        tvDetailsAdapter = TvDetailsAdapter(mutableListOf(), viewModel)
        binding.nestedRecycler.adapter = tvDetailsAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                val tvDetailsItems = mutableListOf(
                    TvDetailsItem.Upper(state.info),
                    TvDetailsItem.People(state.cast),
                    TvDetailsItem.Recommended(state.recommended)
                ) + state.seasons.map { TvDetailsItem.Season(it) } + state.reviews.map {
                    TvDetailsItem.Review(
                        it
                    )
                }
                tvDetailsAdapter.setItems(tvDetailsItems)
            }
        }
    }

    private fun showBottomSheet() {
        RateBottomSheet().show(childFragmentManager, "BOTTOM")
    }
}
