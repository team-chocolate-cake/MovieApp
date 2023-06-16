package com.chocolatecake.ui.movie_guessing

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentMoiveGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.movie_guessing.MovieGuessingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieGuessingFragment :
    BaseFragment<FragmentMoiveGuessingBinding, GameUiState, GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_moive_guessing
    override val viewModel: MovieGuessingViewModel by viewModels()

    override fun onEvent(event: GameUIEvent) {
        when(event){
            GameUIEvent.NavigateToLoserScreen -> navigateToWelcomeGameScreen()
            is GameUIEvent.NavigateToWinnerScreen -> navigateToWinnerScreen(event.level,event.points)
            GameUIEvent.ShowTimeOut -> showTimeOut()
            is GameUIEvent.UpdateQuestion -> updateQuestion(event.questionNumber)
        }
    }

    private fun navigateToWelcomeGameScreen() {
        TODO("Not yet implemented")
    }

    private fun navigateToWinnerScreen(level: Int, points: Int) {

    }

    private fun showTimeOut() {
        TODO("Not yet implemented")
    }

    private fun updateQuestion(questionNumber: Int) {

    }
}