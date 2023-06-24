package com.chocolatecake.ui.people_guessing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentPeopleGuessingBinding
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import com.chocolatecake.viewmodel.people_guessing.PeopleGuessingViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PeopleGuessingFragment :
    BaseFragment<FragmentPeopleGuessingBinding, GameUiState, GameUIEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_people_guessing
    override val viewModel: PeopleGuessingViewModel by viewModels()

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
        when (event) {
            GameUIEvent.NavigateToLoserScreen -> {
                showSnackBar("Game Over !👍")
                findNavController().navigate(PeopleGuessingFragmentDirections.actionPeopleGuessingFragmentToTypeGameFragment())
            }

            is GameUIEvent.NavigateToWinnerScreen -> {
                findNavController().navigate(
                    PeopleGuessingFragmentDirections.actionPeopleGuessingFragmentToCongratsFragment(
                        event.gameType
                    )
                )
            }

            GameUIEvent.ShowTimeOut -> {
                showSnackBar(getString(R.string.time_out))
            }

            is GameUIEvent.ShowSnackbar -> showSnackBar(event.message)

            is GameUIEvent.ShowBuyHeartDialog -> showDialog(event.numberOfPoints)
        }
    }
    private fun showDialog(numberOfPoints: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.trivai_lost_title))
            .setMessage(getString(R.string.trivia_losing_meesage, numberOfPoints))
            .setNeutralButton(getString(R.string.trivia_cancel_game)) { _, _ ->
                findNavController().navigate(PeopleGuessingFragmentDirections.actionPeopleGuessingFragmentToTypeGameFragment())
            }
            .setPositiveButton(getString(R.string.trivai_buy_dialog)) { _, _ ->
                viewModel.buyHearts(numberOfPoints)
            }
            .show()
    }
}