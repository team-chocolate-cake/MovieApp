package com.chocolatecake.movieapp.ui.login

data class LoginUiState(
    val userName: String = "",
    val userNameError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val isLoading: Boolean = false,
    val requestError:Boolean=false
)