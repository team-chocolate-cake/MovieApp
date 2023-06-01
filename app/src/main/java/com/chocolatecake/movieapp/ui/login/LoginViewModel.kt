package com.chocolatecake.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    fun login() {
        val userName = _loginState.value.userName
        val password = _loginState.value.password

        _loginState.update {
            it.copy(
                userNameError = if (userName.isEmpty()) "Username is required" else null,
                passwordError = if (password.isEmpty()) "Password is required" else null
            )
        }

        viewModelScope.launch {
            _loginState.update { it.copy(onLoading = true) }

            val success = loginUseCase(userName, password)

            if (success) {
                _loginState.update {
                    it.copy(
                        userNameError = null,
                        passwordError = null,
                        onLoading = false
                    )
                }

            } else {
                _loginState.update {
                    it.copy(
                        userName = "",
                        password = "",
                        passwordError = "Login failed",
                        onLoading = false
                    )
                }
            }
        }
    }

    fun onUserNameChanged(userName: String) {
        _loginState.update { it.copy(userName = userName, userNameError = null) }
        Log.d("mimoo", "password $userName")
    }

    fun onPasswordChanged(password: String) {
        _loginState.update { it.copy(password = password, passwordError = null) }
        Log.d("mimoo", "password $password")
    }
}
