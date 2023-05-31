package com.chocolatecake.movieapp.ui.login

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val onError: String = "",
    val onLoading: Boolean = false
)