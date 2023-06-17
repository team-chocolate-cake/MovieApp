package com.chocolatecake.ui.tv_show_guessing

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentTvShowGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.tv_show_guessing.TvShowGuessingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowGuessingFragment: BaseFragment<FragmentTvShowGuessingBinding, GameUiState, GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_tv_show_guessing
    override val viewModel: TvShowGuessingViewModel by viewModels()

    override fun onEvent(event: GameUIEvent) {
//        when(event){
//            GameUIEvent.NavigateToLoserScreen -> navigateToWelcomeGameScreen()
//            is GameUIEvent.NavigateToWinnerScreen -> navigateToWinnerScreen(event.level,event.points)
//            GameUIEvent.ShowTimeOut -> showTimeOut()
//            is GameUIEvent.UpdateQuestion -> updateQuestion(event.questionNumber)
//        }
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