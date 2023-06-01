package com.chocolatecake.movieapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private val loginState = MutableStateFlow(LoginUiState())

    val userName = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun login() {
        loginState.update { it.copy(userName = userName.value, password = password.value) }
        viewModelScope.launch {
            loginUseCase(userName.value, password.value)
        }
    }
}