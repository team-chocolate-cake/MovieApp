package com.chocolatecake.ui.eposideDetails

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment

import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentEpisodeDetailsBinding
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsUiEvent
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsUiState
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailsFragment :
    BaseFragment<FragmentEpisodeDetailsBinding, EpisodeDetailsUiState, EpisodeDetailsUiEvent>() {

    override val layoutIdFragment = R.layout.fragment_episode_details
    override val viewModel: EpisodeDetailsViewModel by viewModels()

    override fun onEvent(event: EpisodeDetailsUiEvent) {

    }
}