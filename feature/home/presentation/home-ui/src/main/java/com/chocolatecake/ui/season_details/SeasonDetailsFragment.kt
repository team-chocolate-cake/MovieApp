package com.chocolatecake.ui.season_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentSeasonDetailsBinding
import com.chocolatecake.viewmodel.season_details.SeasonDetailsUiEvent
import com.chocolatecake.viewmodel.season_details.SeasonDetailsUiState
import com.chocolatecake.viewmodel.season_details.SeasonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SeasonDetailsFragment () : BaseFragment
<FragmentSeasonDetailsBinding, SeasonDetailsUiState, SeasonDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_season_details
    override val viewModel : SeasonDetailsViewModel by viewModels()

    private val seasonDetailsAdapter: SeasonDetailsAdapter
            by lazy { SeasonDetailsAdapter(mutableListOf(),viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewEpisodes.adapter = seasonDetailsAdapter
        collectChange()
    }

    private fun collectChange(){
        collectLatest {
            viewModel.state.collectLatest{ state ->
            val seasonDetailsItems = mutableListOf(
                SeasonDetailsItem.OverviewItem(state.overview))+
                state.episodes.map {
                    SeasonDetailsItem.EpisodeItem(it)
                }
                seasonDetailsAdapter.setItems(seasonDetailsItems)
            }
            }
        }
    }

    override fun onEvent(event: SeasonDetailsUiEvent) {
        when(event){
            is SeasonDetailsUiEvent.NavigateToEpisodeDetails ->
                showSnackBar(event.episodeId.toString()) //ToDo Navigate to Episode Details
            SeasonDetailsUiEvent.NavigateBack -> findNavController().popBackStack()
            is SeasonDetailsUiEvent.ShowSnackBar -> showSnackBar(event.messages)
        }
    }
}