package com.chocolatecake.ui.profile

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.bases.ListName
import com.chocolatecake.bases.ListType
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentProfileBinding
import com.chocolatecake.viewmodel.profile.ProfileUIState
import com.chocolatecake.viewmodel.profile.ProfileUiEvent
import com.chocolatecake.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileUIState, ProfileUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppTheme()
    }

    override fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.NavigateToFavoriteScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movies.name,
                        listId = 0,
                        listName = ListName.favorite.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToWatchlistScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movies.name,
                        listId = 0,
                        listName = ListName.watchlist.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToWatchHistoryScreen -> {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToWatchHistoryFragment())
            }

            ProfileUiEvent.NavigateToMyListsScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListFragment()
                )
            }

            ProfileUiEvent.Logout -> showSnackBar("Logout!")

            is ProfileUiEvent.NavigateWithLink -> {
                findNavController().navigate(event.link)
            }
        }
    }

    private fun changeAppTheme() {
        val switchButtonTheme = binding.switchBottonTheme
        val uiNight =   resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        switchButtonTheme.isChecked = uiNight != Configuration.UI_MODE_NIGHT_NO

        switchButtonTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}