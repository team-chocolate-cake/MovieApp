package com.chocolatecake.movieapp.ui.login

import androidx.annotation.StringRes

sealed interface LoginUiEvent {
    data class LoginEvent(val idLoginScreen: Int) : LoginUiEvent
    data class ShowSnackBar(@StringRes val stringId: Int) : LoginUiEvent

    object SignUpEvent : LoginUiEvent
}