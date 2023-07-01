package com.chocolatecake.ui.type_game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.sound_when_play.SoundManager
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentTypeGamesBinding
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.game_type.GameTypeUIEvent
import com.chocolatecake.viewmodel.game_type.GameTypeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TypeGameFragment : BaseFragment<FragmentTypeGamesBinding, Unit, GameTypeUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_type_games
    override val viewModel: BaseViewModel<Unit, GameTypeUIEvent> by viewModels<GameTypeViewModel>()

    @Inject
    lateinit var soundManager: SoundManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onResume() {
        super.onResume()
        val volumeDrawableRes =
            if (soundManager.isSoundOn) R.drawable.ic_volume_full else R.drawable.ic_volume_mute
        binding.imageButtonVolume.setImageResource(volumeDrawableRes)
    }
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
                 findNavController().navigate(TypeGameFragmentDirections.actionTypeGameFragmentToGameLevelFragment(
                     GameType.TV_SHOW
                 ))
            }

            is GameTypeUIEvent.ShowSnackbar -> {
                showSnackBar(getString(R.string.comming_soon))
            }

            GameTypeUIEvent.PlaySound -> {
                val volumeDrawableRes =
                    if (soundManager.isSoundOn) {R.drawable.ic_volume_mute} else {R.drawable.ic_volume_full}
                binding.imageButtonVolume.setImageResource(volumeDrawableRes)
                soundManager.toggleSound(R.raw.sound)
            }

            GameTypeUIEvent.BackNavigate -> {
                findNavController().popBackStack()
                binding.imageButtonVolume.setImageResource(R.drawable.ic_volume_mute)
                soundManager.stopSound()
            }

        }
    }
}