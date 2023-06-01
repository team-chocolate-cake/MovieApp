package com.chocolatecake.movieapp.ui.login

sealed class LoginFormEvent {
    data class EmailChanged(val userName: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()
}