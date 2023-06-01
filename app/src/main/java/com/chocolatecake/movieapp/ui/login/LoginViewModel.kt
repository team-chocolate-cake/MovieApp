package com.chocolatecake.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    fun login() {
        val userName = _loginState.value.userName
        val password = _loginState.value.password

        if (userName.isEmpty() || password.isEmpty()) {
            _loginState.value = _loginState.value.copy(
                userNameError = if (userName.isEmpty()) "Username is required" else null,
                passwordError = if (password.isEmpty()) "Password is required" else null
            )
            return
        }

        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(onLoading = true)

            val success = loginUseCase(userName, password)

            _loginState.value = if (success) {
                _loginState.value.copy(
                    userNameError = null,
                    passwordError = null,
                    onLoading = false
                )
            } else {
                _loginState.value.copy(
                    userName = "",
                    password = "",
                    passwordError = "Login failed",
                    onLoading = false
                )
            }
        }
    }

    fun onUserNameChanged(userName: String) {
        _loginState.value = _loginState.value.copy(userName = userName, userNameError = null)
        Log.d("mimoo","password $userName")
    }
    fun onPasswordChanged(password: String) {
        _loginState.value = _loginState.value.copy(password = password, passwordError = null)
        Log.d("mimoo","password $password")
    }
}
