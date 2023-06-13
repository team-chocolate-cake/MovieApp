package com.chocolatecake.viewmodel

data class LoginUiState(
    var userName: String = "",
    val userNameError: String? = null,
    var password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
)