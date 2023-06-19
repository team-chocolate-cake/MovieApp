package com.chocolatecake.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentProfileBinding
import com.chocolatecake.viewmodel.profile.ProfileUIState
import com.chocolatecake.viewmodel.profile.ProfileUiEvent
import com.chocolatecake.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileUIState, ProfileUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()
//    private lateinit var uiModeManager: UiModeManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        uiModeManager = requireContext().getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
//        binding.uiMode = uiModeManager
    }

    override fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.FavoriteEvent -> {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(""))
            }
            ProfileUiEvent.LogoutEvent -> {
                showSnackBar("Logout!")
            }

            ProfileUiEvent.MyListsEvent -> TODO()
            ProfileUiEvent.PopcornPuzzlesEvent -> {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToGameNavGraph())
            }

            ProfileUiEvent.RatingEvent -> TODO()
            ProfileUiEvent.ThemeEvent -> TODO()
            ProfileUiEvent.WatchHistoryEvent -> TODO()
            ProfileUiEvent.WatchlistEvent -> TODO()
        }

}
}