package com.chocolatecake.viewmodel

sealed interface LoginUiEvent {

    data class NavigateToHomeScreen(val route: Int) : LoginUiEvent

    data class ShowSnackBar(val message: String) : LoginUiEvent

    object SignUpEvent : LoginUiEvent
}