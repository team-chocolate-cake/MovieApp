package com.chocolatecake.ui.people_guessing

import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentPeopleGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleGuessingFragment: BaseFragment<FragmentPeopleGuessingBinding,GameUiState,GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_people_guessing
    override val viewModel: BaseViewModel<GameUiState, GameUIEvent>
        get() = TODO("Not yet implemented")

    override fun onEvent(event: GameUIEvent) {
        when(event){
            GameUIEvent.NavigateToWelcomeGameScreen -> TODO()
            is GameUIEvent.NavigateToWinnerScreen -> TODO()
            GameUIEvent.ShowTimeOut -> TODO()
            is GameUIEvent.UpdateQuestion -> TODO()
        }
    }
}