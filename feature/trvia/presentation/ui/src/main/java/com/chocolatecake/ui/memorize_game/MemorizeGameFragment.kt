package com.chocolatecake.ui.memorize_game

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentMemorizeBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.memorize_game.MemorizeGameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemorizeGameFragment: BaseFragment<FragmentMemorizeBinding, GameUiState, GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_memorize
    override val viewModel: MemorizeGameViewModel by viewModels()

    override fun onEvent(event: GameUIEvent) {
        when(event){
            GameUIEvent.NavigateToWelcomeGameScreen -> navigateToWelcomeGameScreen()
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