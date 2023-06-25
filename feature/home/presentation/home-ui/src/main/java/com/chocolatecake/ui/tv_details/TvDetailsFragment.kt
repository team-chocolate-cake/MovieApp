package com.chocolatecake.ui.tv_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentTvDetailsBinding
import com.chocolatecake.ui.tv_details.adapter.TvDetailsAdapter
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiEvent
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import com.chocolatecake.viewmodel.tv_details.TvDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class TvDetailsFragment :
    BaseFragment<FragmentTvDetailsBinding, TvDetailsUiState, TvDetailsUiEvent>(),
    BottomSheetDismissListener, WatchlistFavouriteListener {

    private lateinit var rateBottomSheet: RateBottomSheet
    private lateinit var addToWatchlistFavouriteBottomSheet: AddToWatchlistFavouriteBottomSheet
    private lateinit var tvDetailsAdapter: TvDetailsAdapter
    private val args: TvDetailsFragmentArgs by navArgs()

    override val layoutIdFragment: Int = R.layout.fragment_tv_details
    override val viewModel: TvDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectChange()
        collapseState()
    }

    override fun onEvent(event: TvDetailsUiEvent) {
        when (event) {
            is TvDetailsUiEvent.Rate -> showRateBottomSheet()
            is TvDetailsUiEvent.OnPersonClick -> showSnackBar("Actor id ${event.id}")
            is TvDetailsUiEvent.OnSeasonClick -> {
                findNavController()
                    .navigate(
                        TvDetailsFragmentDirections.actionTvDetailsFragmentToSeasonDetailsFragment(
                            args.tvShowId,
                            event.seasonNumber
                        )
                    )
            }

            is TvDetailsUiEvent.OnRecommended -> navigateToTvDetails(event.id)
            is TvDetailsUiEvent.Back -> navigateBack()
            is TvDetailsUiEvent.ApplyRating -> showSnackBar(event.message)
            is TvDetailsUiEvent.OnShowMoreCast -> showSnackBar("Show More Cast")
            is TvDetailsUiEvent.OnShowMoreRecommended -> showSnackBar("Show More Recommended")
            is TvDetailsUiEvent.PlayButton -> showSnackBar("youtube key => ${event.youtubeKey}")
            is TvDetailsUiEvent.OnSaveButtonClick -> showAddToWatchlistFavouriteBottomSheet()
            is TvDetailsUiEvent.OnDoneAdding -> showSnackBar(event.message)
            is TvDetailsUiEvent.onCreateNewList -> showSnackBar(event.message)
            is TvDetailsUiEvent.OnFavourite -> showSnackBar(event.message)
            is TvDetailsUiEvent.OnWatchList -> showSnackBar(event.message)
        }
    }

    private fun setAdapter() {
        tvDetailsAdapter = TvDetailsAdapter(mutableListOf(), viewModel)
        binding.nestedRecycler.adapter = tvDetailsAdapter
    }
    //region navigation
    private fun navigateToTvDetails(tvId: Int) {
        findNavController().navigate(TvDetailsFragmentDirections.actionTvDetailsFragmentSelf(tvId))
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }
    //endregion

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                Log.d("123123123", "collectChange: ${state.recommended}")
                val tvDetailsItems = mutableListOf(
                    TvDetailsItem.Upper(state.info),
                    TvDetailsItem.People(state.cast, state.seasons.isNotEmpty()),
                    TvDetailsItem.Recommended(state.recommended,state.reviews.isNotEmpty())
                ) + state.seasons.map { TvDetailsItem.Season(it) } + state.reviews.map {
                    TvDetailsItem.Review(
                        it
                    )
                }
                tvDetailsAdapter.setItems(tvDetailsItems)
            }
        }
    }
    //region rating bottom sheet
    private fun showRateBottomSheet() {
        rateBottomSheet = RateBottomSheet()
        rateBottomSheet.setListener(this)
        rateBottomSheet.show(childFragmentManager, "BOTTOM")
    }


    override fun onApplyRateBottomSheet() {
        viewModel.onRatingSubmit()
    }

    override fun updateRatingValue(rate: Float) {
        viewModel.updateRatingUiState(rate)
    }
    //endregion

    //region collapse toolbar
    private fun collapseState() {
        var pos = 0
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            binding.nestedRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val firstVisibleItemPosition = recyclerView.layoutManager as LinearLayoutManager
                    pos = firstVisibleItemPosition.findFirstVisibleItemPosition()
                }
            })
            binding.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                when {
                    // Fully expanded state
                    verticalOffset == 0 -> {
                        binding.textViewToolBarName.visibility = View.INVISIBLE
                        if (pos != 0) appBarLayout.setExpanded(false, false)
                    }
                    // Fully collapsed state
                    abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                        binding.textViewToolBarName.visibility = View.VISIBLE
                    }
                    // In between expanded and collapsed states
                    else -> {
                        binding.textViewToolBarName.visibility = View.INVISIBLE
                    }
                }
            }

        }
    }
    //endregion

    //region save to bottom sheet
    private fun showAddToWatchlistFavouriteBottomSheet() {
        binding.saveButton.setBackgroundResource(com.chocolatecake.bases.R.drawable.ic_save_pressed)
        addToWatchlistFavouriteBottomSheet = AddToWatchlistFavouriteBottomSheet(this)
        addToWatchlistFavouriteBottomSheet.show(childFragmentManager, "BOTTOM")
    }

    override fun onFavourite() {
        viewModel.addToFavourite()
    }

    override fun onWatchlist() {
        viewModel.addToWatchlist()
    }

    override fun onDismiss() {
        binding.saveButton.setBackgroundResource(com.chocolatecake.bases.R.drawable.ic_save_unpressed)
    }
    //endregion

}
