package com.chocolatecake.ui.type_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BR
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentTypeGamesBinding
import com.chocolatecake.viewmodel.game_type.GameTypeUIEvent
import com.chocolatecake.viewmodel.game_type.GameTypeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TypeGameFragment: Fragment() {
    lateinit var binding: FragmentTypeGamesBinding
    val viewModel: GameTypeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_type_games, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLatest { viewModel.event.collectLatest { onEvent(it) } }
    }

    private fun onEvent(gameTypeUIEvent: GameTypeUIEvent) {
        when(gameTypeUIEvent){
            GameTypeUIEvent.NavigateToMemorizeBoard -> {
                findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToMemorizeGameFragment())
            }
            GameTypeUIEvent.NavigateToMovieGameBoard -> {
                findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToMovieGuessingFragment())
            }
            GameTypeUIEvent.NavigateToPeopleGameBoard -> {
                findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToPeopleGuessingFragment())
            }
            GameTypeUIEvent.NavigateToTvGameBoard -> {
                findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToTvShowGuessingFragment())
            }
            is GameTypeUIEvent.ShowSnackbar -> {
                showSnackBar()
            }
        }
    }

    private fun collectLatest(collect: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect()
            }
        }
    }

    private fun showSnackBar() {
        Snackbar.make(binding.root, "Comming Soon!!!", Snackbar.LENGTH_SHORT).show()
    }
}