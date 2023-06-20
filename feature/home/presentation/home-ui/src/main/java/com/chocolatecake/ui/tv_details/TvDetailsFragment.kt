package com.chocolatecake.ui.tv_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvDetailsBinding
import com.chocolatecake.ui.tv_details.adapter.TvDetailsAdapter
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiEvent
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvDetailsFragment :
    BaseFragment<FragmentTvDetailsBinding, TvDetailsUiState, TvDetailsUiEvent>(),
    BottomSheetDismissListener {

    private lateinit var bottomSheet: RateBottomSheet
    private lateinit var tvDetailsAdapter: TvDetailsAdapter
    override val layoutIdFragment: Int = R.layout.fragment_tv_details
    override val viewModel: TvDetailsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        collectChange()
    }


    override fun onEvent(event: TvDetailsUiEvent) {
        when (event) {
            is TvDetailsUiEvent.Rate -> showBottomSheet()
            is TvDetailsUiEvent.OnPersonClick -> showSnackBar("Actor id ${event.id}")
            is TvDetailsUiEvent.OnSeasonClick -> showSnackBar("season id ${event.id}")
            is TvDetailsUiEvent.OnRecommended -> navigateToSeasonDetails(event.id)
            is TvDetailsUiEvent.Back -> navigateBack()
            is TvDetailsUiEvent.ApplyRating -> showSnackBar(event.message)
            is TvDetailsUiEvent.OnShowMoreCast ->showSnackBar("Show More Cast")
            is TvDetailsUiEvent.OnShowMoreRecommended ->showSnackBar("Show More Recommended")
            else -> {
            }
        }
    }

    private fun setAdapter() {
        tvDetailsAdapter = TvDetailsAdapter(mutableListOf(), viewModel)
        binding.nestedRecycler.adapter = tvDetailsAdapter
    }

    private fun navigateToSeasonDetails(seasonId: Int) {
        findNavController().navigate(TvDetailsFragmentDirections.actionHomeFragmentSelf(seasonId))
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                val tvDetailsItems = mutableListOf(
                    TvDetailsItem.Upper(state.info),
                    TvDetailsItem.People(state.cast),
                    TvDetailsItem.Recommended(state.recommended)
                ) + state.seasons.map { TvDetailsItem.Season(it) } +
                        state.reviews.map { TvDetailsItem.Review(it) }
                tvDetailsAdapter.setItems(tvDetailsItems)
            }
        }
    }

    private fun showBottomSheet() {
        bottomSheet = RateBottomSheet()
        bottomSheet.setListener(this)
        bottomSheet.show(childFragmentManager, "BOTTOM")
    }

    override fun onBottomSheetDismissed() {
        viewModel.onRatingSubmit()
    }

    override fun passRatingValue(rate: Float) {
        viewModel.passRatingValue(rate)
    }

}
