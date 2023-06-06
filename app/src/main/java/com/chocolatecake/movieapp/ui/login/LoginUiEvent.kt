package com.chocolatecake.movieapp.ui.login

import androidx.annotation.StringRes

sealed interface LoginUiEvent {
     object LoginEvent : LoginUiEvent
    data class ShowSnackBar(@StringRes val stringId: Int) : LoginUiEvent

    object SignUpEvent : LoginUiEvent
}