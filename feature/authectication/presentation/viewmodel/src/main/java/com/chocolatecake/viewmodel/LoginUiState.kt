package com.chocolatecake.viewmodel

data class LoginUiState(
    val userName: String = "",
    val userNameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
)