package com.chocolatecake.ui.type_game

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentTypeGamesBinding
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.game_type.GameTypeUIEvent
import com.chocolatecake.viewmodel.game_type.GameTypeViewModel
import com.chocolatecake.ui.sound_when_play.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TypeGameFragment : BaseFragment<FragmentTypeGamesBinding, Unit, GameTypeUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_type_games
    override val viewModel: BaseViewModel<Unit, GameTypeUIEvent> by viewModels<GameTypeViewModel>()
    @Inject lateinit var soundManager : SoundManager

    override fun onEvent(event: GameTypeUIEvent) {
        when (event) {
            GameTypeUIEvent.NavigateToMemorizeBoard -> {
                findNavController().navigate(
                    TypeGameFragmentDirections.actionTypeGameFragmentToGameLevelFragment(
                        GameType.MEMORIZE
                    )
                )
            }

            GameTypeUIEvent.NavigateToMovieGame -> {
                findNavController().navigate(
                    TypeGameFragmentDirections.actionTypeGameFragmentToGameLevelFragment(
                        GameType.MOVIE
                    )
                )
            }

            GameTypeUIEvent.NavigateToPeopleGame -> {
                findNavController().navigate(
                    TypeGameFragmentDirections.actionTypeGameFragmentToGameLevelFragment(
                        GameType.PEOPLE
                    )
                )
            }

            GameTypeUIEvent.NavigateToTvGame -> {
                showSnackBar("Not Supported Yet !🤷‍")
                // findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToTvShowGuessingFragment())
            }

            is GameTypeUIEvent.ShowSnackbar -> {
                showSnackBar("Coming Soon!!!")
            }

            GameTypeUIEvent.PlaySound -> {
                soundManager.toggleSound(R.raw.sound)
            }
            GameTypeUIEvent.BackNavigate->{
                findNavController().popBackStack()
                soundManager.stopSound()
            }

        }
    }
}