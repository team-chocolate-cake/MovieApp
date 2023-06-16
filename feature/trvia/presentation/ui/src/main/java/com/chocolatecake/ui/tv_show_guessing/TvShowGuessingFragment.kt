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
        when(event){
            GameUIEvent.NavigateToWelcomeGameScreen -> TODO()
            is GameUIEvent.NavigateToWinnerScreen -> TODO()
            GameUIEvent.ShowTimeOut -> TODO()
            is GameUIEvent.UpdateQuestion -> TODO()
        }
    }
}