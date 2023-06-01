package com.chocolatecake.movieapp.ui.login

data class LoginUiState(
    val userName: String = "",
    val userNameError: String? = null,
    val password: String = "",
    val passwordNameError: String? = null,
    val onLoading: Boolean = false
)