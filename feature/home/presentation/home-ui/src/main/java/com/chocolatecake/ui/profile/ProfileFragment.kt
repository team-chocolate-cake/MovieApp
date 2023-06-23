package com.chocolatecake.ui.profile

import android.content.Context
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

    companion object {
        private const val PREF_THEME_STATE = "theme_state"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppTheme()
    }

    override fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.NavigateToFavoriteScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movie.name,
                        listId = 0,
                        listName = ListName.favorite.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToWatchlistScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListFragment()
                )
            }

            ProfileUiEvent.NavigateToWatchHistoryScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movie.name,
                        listId = 0,
                        listName = ListName.watchlist.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToMyListsScreen -> showSnackBar("MyLists")
            ProfileUiEvent.Logout -> showSnackBar("Logout!")

            is ProfileUiEvent.NavigateWithLink -> {
                findNavController().navigate(event.link)
            }
        }
    }

    private fun changeAppTheme() {
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val switchButtonTheme = binding.switchBottonTheme
        val savedThemeState = sharedPreferences.getBoolean(PREF_THEME_STATE, false)
        switchButtonTheme.isChecked = savedThemeState

        switchButtonTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(PREF_THEME_STATE, isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}