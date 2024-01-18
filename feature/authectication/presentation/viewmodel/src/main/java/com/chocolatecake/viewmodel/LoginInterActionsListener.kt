package com.chocolatecake.viewmodel

interface LoginInterActionsListener {
    fun onUsernameChanged(username: String)
    fun onPasswordChanged(password: String)
}