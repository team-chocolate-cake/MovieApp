package com.chocolatecake.ui.eposideDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.common.adapters.PeopleAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentEpisodeDetailsBinding
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsUiEvent
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsUiState
import com.chocolatecake.viewmodel.eposideDetails.EpisodeDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeDetailsFragment :
    BaseFragment<FragmentEpisodeDetailsBinding, EpisodeDetailsUiState, EpisodeDetailsUiEvent>() {

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

    }

    private fun setAdapter() {
        setItemsInAdapter()
        binding.recyclerViewPeople.adapter = peopleAdapter
    }

    private fun setItemsInAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                peopleAdapter.setItems(state.cast)
                Log.d("banan-list",state.cast.toString())
            }
        }
    }

    override fun onEvent(event: EpisodeDetailsUiEvent) {

    }
}