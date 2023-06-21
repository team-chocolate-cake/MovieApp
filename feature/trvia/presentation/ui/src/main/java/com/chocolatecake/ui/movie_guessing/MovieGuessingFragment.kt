package com.chocolatecake.ui.movie_guessing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentMoiveGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.movie_guessing.MovieGuessingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MovieGuessingFragment :
    BaseFragment<FragmentMoiveGuessingBinding, GameUiState, GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_moive_guessing
    override val viewModel: MovieGuessingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeItemCardChoosePeople.listener = viewModel
        initData()
    }

    private fun initData() {
        collectLatest {
            viewModel.state.collectLatest { state ->
                binding.includeItemHeaderGame.item = state
                binding.includeItemCardChoosePeople.item = state
            }
        }
    }

    override fun onEvent(event: GameUIEvent) {
        when(event){
            GameUIEvent.NavigateToLoserScreen -> {
                findNavController().navigate(MovieGuessingFragmentDirections.actionMovieGuessingFragmentToTypeGameFragment())
            }

            is GameUIEvent.NavigateToWinnerScreen -> {
                findNavController().navigate(
                    MovieGuessingFragmentDirections.actionMovieGuessingFragmentToCongratsFragment(
                        event.gameType
                    )
                )
            }

            GameUIEvent.ShowTimeOut -> {
                showSnackBar(getString(R.string.time_out))
            }

            is GameUIEvent.ShowSnackbar -> showSnackBar(event.message)
        }
    }
}