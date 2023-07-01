package com.chocolatecake.ui.episode_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.common.adapters.PeopleAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentEpisodeDetailsBinding
import com.chocolatecake.viewmodel.episode_details.EpisodeDetailsUiEvent
import com.chocolatecake.viewmodel.episode_details.EpisodeDetailsUiState
import com.chocolatecake.viewmodel.episode_details.EpisodeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailsFragment :
    BaseFragment<FragmentEpisodeDetailsBinding, EpisodeDetailsUiState, EpisodeDetailsUiEvent>(),
    BottomSheetListener {

    override val layoutIdFragment = R.layout.fragment_episode_details
    override val viewModel: EpisodeDetailsViewModel by viewModels()
    private val peopleAdapter by lazy { PeopleAdapter(mutableListOf(), viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onEvent(event: EpisodeDetailsUiEvent) {
        when (event) {
            is EpisodeDetailsUiEvent.ClickToBack -> navigateToBack()
            is EpisodeDetailsUiEvent.ClickToRate -> checkIsLoggedInOrNot()
            is EpisodeDetailsUiEvent.ClickCast -> navigateToCastDetails(event.itemId)
            is EpisodeDetailsUiEvent.SubmitRating -> showSnackBar(event.message)
            is EpisodeDetailsUiEvent.ClickToPlayFullScreen -> navigateToPlayFullScreen(event.videoKey)
        }
    }

    private fun checkIsLoggedInOrNot() {
        val isLoggedIn = viewModel.state.value.isLoggedIn
        if (isLoggedIn) {
            showBottomSheet()
        } else {
            showSnackBar("You are not logged in \uD83D\uDE22, please log in to rate this episode");
        }
    }

    private fun showBottomSheet() {
        val bottomSheet = EpisodeRateBottomSheet()
        bottomSheet.show(childFragmentManager, "BOTTOM")
        bottomSheet.setListener(this)
    }


    private fun navigateToPlayFullScreen(videoKey: String) {
        findNavController().navigate(
            EpisodeDetailsFragmentDirections.actionEpisodeDetailsFragmentToTrailerFragment(
                videoKey
            )
        )
    }

    override fun onApplyRateBottomSheet() {
        viewModel.setRating()
    }

    override fun updateRatingValue(rate: Float) {
        viewModel.updateRatingState(rate)
    }

    private fun setAdapter() {
        collectLatest { peopleAdapter.setItems(viewModel.state.value.cast) }
        binding.recyclerViewPeople.adapter = peopleAdapter
    }

    private fun navigateToBack() {
        findNavController().popBackStack()
    }

    private fun navigateToCastDetails(itemId: Int) {
        findNavController().navigate(
            EpisodeDetailsFragmentDirections.actionEpisodeDetailsFragmentToPeopleDetailsFragment(
                itemId
            )
        )
    }
}