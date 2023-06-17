package com.chocolatecake.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentProfileBinding
import com.chocolatecake.viewmodel.profile.ProfileUIState
import com.chocolatecake.viewmodel.profile.ProfileUiEvent
import com.chocolatecake.viewmodel.home.HomeViewModel
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