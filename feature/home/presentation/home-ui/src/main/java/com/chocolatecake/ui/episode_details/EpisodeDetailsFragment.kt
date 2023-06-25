package com.chocolatecake.ui.episode_details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest {
                binding.item = it
            }
        }

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun setAdapter() {
        collectLatest { peopleAdapter.setItems(viewModel.state.value.cast) }
        binding.recyclerViewPeople.adapter = peopleAdapter
    }

    override fun onEvent(event: EpisodeDetailsUiEvent) {
        when (event) {
            is EpisodeDetailsUiEvent.ClickToRate -> showBottomSheet()
            is EpisodeDetailsUiEvent.ClickCast -> navigateToCastDetails()
            is EpisodeDetailsUiEvent.ClickToBack -> navigateToBack()
            is EpisodeDetailsUiEvent.SubmitRating -> showSnackBar(event.message)
        }
    }

    private fun navigateToBack() {
        findNavController().popBackStack()
        Toast.makeText(requireActivity(), "back button clicked", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCastDetails() {
        //toDo findNavController().navigate()
    }

    private fun showBottomSheet() {
        val bottomSheet = EpisodeRateBottomSheet()
        bottomSheet.show(childFragmentManager, "BOTTOM")
        bottomSheet.setListener(this)
    }

    override fun onApplyRateBottomSheet() {
        viewModel.setRating()
    }

    override fun updateRatingValue(rate: Float) {
        viewModel.updateRatingState(rate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}