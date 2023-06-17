package com.chocolatecake.viewmodel.profile

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.profile.GetAccountDetailsUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val profileUiMapper: ProfileUiMapper
): BaseViewModel <ProfileUIState, ProfileUiEvent>(ProfileUIState()),ProfileListener
{
    private fun getAccountDetails(){
        viewModelScope.launch {
            profileUiMapper.map(getAccountDetailsUseCase())
        }
    }

    override fun onClickFavorite() {
        sendEvent(ProfileUiEvent.FavoriteEvent)
    }

    override fun onClickWatchlist() {
        sendEvent(ProfileUiEvent.WatchlistEvent)
    }

    override fun onClickWatchHistory() {
        sendEvent(ProfileUiEvent.WatchHistoryEvent)    }

    override fun onClickMyLists() {
        sendEvent(ProfileUiEvent.MyListsEvent)    }

    override fun onClickRating() {
        sendEvent(ProfileUiEvent.RatingEvent)    }

    override fun onClickPopcornPuzzles() {
        sendEvent(ProfileUiEvent.PopcornPuzzlesEvent)    }

    override fun onClickTheme() {
        sendEvent(ProfileUiEvent.ThemeEvent)    }

    override fun onClickLogout() {
        sendEvent(ProfileUiEvent.LogoutEvent)    }
}