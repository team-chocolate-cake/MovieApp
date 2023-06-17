package com.chocolatecake.ui.profile

import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentProfileBinding
import com.chocolatecake.viewmodel.profile.ProfileUIState
import com.chocolatecake.viewmodel.profile.ProfileUiEvent
import com.chocolatecake.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding, ProfileUIState, ProfileUiEvent>() {

    override val layoutIdFragment: Int =R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()
    override fun onEvent(event: ProfileUiEvent) {
        when(event){
            ProfileUiEvent.FavoriteEvent -> TODO()
            ProfileUiEvent.LogoutEvent -> TODO()
            ProfileUiEvent.MyListsEvent -> TODO()
            ProfileUiEvent.PopcornPuzzlesEvent -> TODO()
            ProfileUiEvent.RatingEvent -> TODO()
            ProfileUiEvent.ThemeEvent -> TODO()
            ProfileUiEvent.WatchHistoryEvent -> TODO()
            ProfileUiEvent.WatchlistEvent -> TODO()
        }

}
}