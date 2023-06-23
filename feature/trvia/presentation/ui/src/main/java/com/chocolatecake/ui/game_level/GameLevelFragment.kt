package com.chocolatecake.ui.game_level

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentLevelGameBinding
import com.chocolatecake.viewmodel.game_level.GameLevelUIEvent
import com.chocolatecake.viewmodel.game_level.GameLevelUIState
import com.chocolatecake.viewmodel.game_level.GameLevelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class GameLevelFragment :
    BaseFragment<FragmentLevelGameBinding, GameLevelUIState, GameLevelUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_level_game
    override val viewModel by viewModels<GameLevelViewModel>()
    private lateinit var adapter: GameLevelAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameLevelAdapter(mutableListOf(), viewModel)
        binding.recyclerViewLevelGames.adapter = adapter
        collectLatest { viewModel.state.collectLatest { state -> adapter.setItems(state.gameLevel) } }
    }

    override fun onEvent(event: GameLevelUIEvent) {
        when (event) {
            GameLevelUIEvent.NavigateToMemorizeBoard -> {
                findNavController().navigate(GameLevelFragmentDirections.actionGameLevelFragmentToMemorizeGameFragment())
            }

            GameLevelUIEvent.NavigateToMovieGame -> {
                findNavController().navigate(GameLevelFragmentDirections.actionGameLevelFragmentToMovieGuessingFragment())
            }

            GameLevelUIEvent.NavigateToPeopleGame -> {
                findNavController().navigate(GameLevelFragmentDirections.actionGameLevelFragmentToPeopleGuessingFragment())
            }

            GameLevelUIEvent.NavigateToTvGame -> {
                findNavController().navigate(GameLevelFragmentDirections.actionGameLevelFragmentToTvShowGuessingFragment())
            }

            is GameLevelUIEvent.ShowYouMustPassPreviousLevelFirst -> {
                showSnackBar(getString(R.string.pass_prev_level))
            }

            is GameLevelUIEvent.ShowSnckbar -> showSnackBar(event.message)
        }
    }
}