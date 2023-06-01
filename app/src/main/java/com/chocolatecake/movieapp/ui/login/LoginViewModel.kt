package com.chocolatecake.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.data.remote.response.auth.SessionResponse
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {

    private val fragmentState = MutableStateFlow(LoginUiState())

    val userName = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun login() {
        viewModelScope.launch {
            loginUseCase(userName.value, password.value)
        }
    }
}