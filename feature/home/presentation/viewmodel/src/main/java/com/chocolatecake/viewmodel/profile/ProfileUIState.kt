package com.chocolatecake.viewmodel.profile

data class ProfileUIState(
    val username: String = "",
    val avatarUrl: String = "",
    val error: List<String>? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)
