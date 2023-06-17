package com.chocolatecake.ui.congrats

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentCongratsBinding
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.congrats.CongratsUIEvent
import com.chocolatecake.viewmodel.congrats.CongratsUIState
import com.chocolatecake.viewmodel.congrats.CongratsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CongratsFragment :
    BaseFragment<FragmentCongratsBinding, CongratsUIState, CongratsUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_congrats
    override val viewModel: CongratsViewModel by viewModels()

    override fun onEvent(event: CongratsUIEvent) {
        when (event) {
            CongratsUIEvent.NavigateToGameTypeScreen -> navigateToGameTypeScreen()
            is CongratsUIEvent.NavigateToNextLevel -> navigateToNextLevel(event.gameType)
        }
    }

    private fun navigateToGameTypeScreen() {
        findNavController().navigate(CongratsFragmentDirections.actionCongratsFragmentToTypeGameFragment())
    }

    private fun navigateToNextLevel(gameType: GameType) {
        val action = when (gameType) {
            GameType.PEOPLE -> CongratsFragmentDirections.actionCongratsFragmentToPeopleGuessingFragment()
            GameType.MOVIE -> CongratsFragmentDirections.actionCongratsFragmentToMovieGuessingFragment()
            GameType.TV_SHOW -> CongratsFragmentDirections.actionCongratsFragmentToTvShowGuessingFragment()
            GameType.MEMORIZE -> CongratsFragmentDirections.actionCongratsFragmentToMemorizeGameFragment()
        }
        findNavController().navigate(action)
    }
}
