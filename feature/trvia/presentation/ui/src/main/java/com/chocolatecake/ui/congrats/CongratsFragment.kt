package com.chocolatecake.ui.congrats

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentCongratsBinding
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
            CongratsUIEvent.NavigateToLoserScreen -> navigateToLoserScreen()
            CongratsUIEvent.NavigateToWelcomeGameScreen -> navigateToWelcomeGameScreen()
            CongratsUIEvent.NavigateToWinnerScreen -> navigateToWinnerScreen()
            is CongratsUIEvent.ToggleItemGame -> toggleItemGame()
            CongratsUIEvent.NavigateToHome -> navigateToHome()
            CongratsUIEvent.NavigateToNextLevel -> navigateToNextLevel()
            is CongratsUIEvent.UpdateLevel -> updateLevel()
        }
    }

    private fun navigateToLoserScreen() {
        // Navigate To Loser Screen
    }

    private fun navigateToWelcomeGameScreen() {
        // Navigate To Welcome Game Screen
    }

    private fun navigateToWinnerScreen() {
        // Navigate To Winner Screen
    }

    private fun toggleItemGame() {
        // Toggle Item Game
    }

    private fun navigateToHome() {
        // Navigate To Home Screen
    }

    private fun navigateToNextLevel() {
        // Navigate To Next Level Screen
    }

    private fun updateLevel() {
        // Update Level

    }
}
