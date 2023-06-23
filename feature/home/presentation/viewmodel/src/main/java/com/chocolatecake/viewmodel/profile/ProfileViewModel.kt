package com.chocolatecake.viewmodel.profile

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.NavigationRes
import com.chocolatecake.usecase.profile.CheckIsUserLoggedInUseCase
import com.chocolatecake.usecase.profile.GetAccountDetailsUseCase
import com.chocolatecake.usecase.profile.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val profileUiMapper: ProfileUiMapper,
    private val checkIsUserLoggedInUseCase: CheckIsUserLoggedInUseCase,
    private val navigationRes: NavigationRes
) : BaseViewModel<ProfileUIState, ProfileUiEvent>(ProfileUIState()), ProfileListener {

    init {
        getAccountDetails()
    }

    private fun getAccountDetails() {
        viewModelScope.launch {
            try {
                val profileEntity = profileUiMapper.map(getAccountDetailsUseCase())
                _state.update {
                    it.copy(
                        username = profileEntity.username,
                        avatarUrl = profileEntity.avatarUrl,
                        error = null,
                        isLoggedIn = true
                    )
                }
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.error
        _state.update { it.copy(error = errors, isLoading = false) }
    }

    override fun onClickFavorite() {
        sendEvent(ProfileUiEvent.NavigateToFavoriteScreen)
    }

    override fun onClickWatchlist() {
        sendEvent(ProfileUiEvent.NavigateToWatchlistScreen)
    }

    override fun onClickWatchHistory() {
        sendEvent(ProfileUiEvent.NavigateToWatchHistoryScreen)
    }

    override fun onClickMyLists() {
        sendEvent(ProfileUiEvent.NavigateToMyListsScreen)
    }

    override fun onClickPopcornPuzzles() {
        sendEvent(ProfileUiEvent.NavigateWithLink(navigationRes.gameFeatureLink))
    }

    override fun onClickLogout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoggedIn = false) }
            if (_state.value.isLoggedIn == logoutUseCase()) {
                sendEvent(ProfileUiEvent.Logout)
            }
        }
    }

    override fun onUserNotLoggedIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoggedIn = true) }
            if (checkIsUserLoggedInUseCase()) {
                _state.update {
                    it.copy(isLoggedIn = false)
                }
            }

        }
    }

    override fun ocClickLogIn() {
        sendEvent(ProfileUiEvent.NavigateWithLink(navigationRes.authFeatureLink))
    }
}