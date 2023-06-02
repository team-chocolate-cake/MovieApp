package com.chocolatecake.movieapp.ui.login

sealed interface LoginUiEvent {
    data class LoginEvent(val idLoginScreen: Int) : LoginUiEvent
    object SignUpEvent : LoginUiEvent
}