package com.chocolatecake.movieapp.ui.tv_details

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvDetailsBinding
import com.chocolatecake.ui.search.FilterMovieBottomSheetFragment
import com.chocolatecake.ui.tv_details.RateBottomSheet
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiEvent
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailsFragment :
    BaseFragment<FragmentTvDetailsBinding, TvDetailsUiState, TvDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_tv_details
    override val viewModel: TvDetailsViewModel by viewModels()
    override fun onEvent(event: TvDetailsUiEvent) {
        when (event) {
            is TvDetailsUiEvent.Rate -> showBottomSheet()
//            is TvDetailsUiEvent.ApplyRating
            else -> {}
        }
    }

    private fun showBottomSheet() {
        RateBottomSheet().show(childFragmentManager, "BOTTOM")
    }
}