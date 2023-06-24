package com.chocolatecake.ui.gameover

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentGameoverBinding
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.gameover.GameoverUIEvent
import com.chocolatecake.viewmodel.gameover.GameoverUIState
import com.chocolatecake.viewmodel.gameover.GameoverViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameoverFragment :
    BaseFragment<FragmentGameoverBinding, GameoverUIState, GameoverUIEvent>(){
    override val layoutIdFragment: Int = R.layout.fragment_gameover
    override val viewModel: GameoverViewModel by viewModels()

    override fun onEvent(event: GameoverUIEvent) {
        when (event) {
            is GameoverUIEvent.NavigateToGameTypeScreen -> navigateToGameTypeScreen()
            is GameoverUIEvent.NavigateToAnotherQuestion -> navigateToAnotherQuestion(event.gameType)
        }
    }

    private fun navigateToGameTypeScreen() {
        findNavController().navigate(GameoverFragmentDirections.actionGameoverFragmentToTypeGameFragment())
    }

    private fun navigateToAnotherQuestion(gameType: GameType) {
        val action = when (gameType) {
            GameType.PEOPLE -> GameoverFragmentDirections.actionGameoverFragmentToPeopleGuessingFragment()
            GameType.MOVIE -> GameoverFragmentDirections.actionGameoverFragmentToMovieGuessingFragment()
            GameType.TV_SHOW -> GameoverFragmentDirections.actionGameoverFragmentToTvShowGuessingFragment()
            GameType.MEMORIZE -> GameoverFragmentDirections.actionGameoverFragmentToMemorizeGameFragment()
        }
        findNavController().navigate(action)
    }

}