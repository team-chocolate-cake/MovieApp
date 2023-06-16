package com.chocolatecake.ui.people_guessing

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentPeopleGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.people_guessing.PeopleGuessingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleGuessingFragment: BaseFragment<FragmentPeopleGuessingBinding,GameUiState,GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_people_guessing
    override val viewModel: PeopleGuessingViewModel by viewModels()

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