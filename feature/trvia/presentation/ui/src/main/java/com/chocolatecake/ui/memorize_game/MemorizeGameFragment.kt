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
            GameUIEvent.NavigateToWelcomeGameScreen -> TODO()
            is GameUIEvent.NavigateToWinnerScreen -> TODO()
            GameUIEvent.ShowTimeOut -> TODO()
            is GameUIEvent.UpdateQuestion -> TODO()
        }
    }
}