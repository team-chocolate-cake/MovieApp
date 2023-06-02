package com.chocolatecake.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.domain.usecases.auth.GetIsValidLoginUseCase
import com.chocolatecake.movieapp.domain.usecases.auth.LoginStateIndicator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val getIsValidLoginUseCase: GetIsValidLoginUseCase) :
    ViewModel() {
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    fun login() {
        val userName = _loginState.value.userName
        val password = _loginState.value.password

        viewModelScope.launch {
            _loginState.update { it.copy(onLoading = true) }
            when (getIsValidLoginUseCase(userName, password)) {
                LoginStateIndicator.USER_NAME_ERROR -> updateStateToUserNameError()
                LoginStateIndicator.PASSWORD_NAME_ERROR -> updateStateToPasswordError()
                LoginStateIndicator.REQUEST_ERROR -> updateStateToRequestError()
                LoginStateIndicator.SUCCESS -> updateStateToSuccess()
            }
        }
    }

    private fun updateStateToRequestError() {
        _loginState.update {
            it.copy(
                requestError = true,
                onLoading = false
            )
        }
    }
    private fun updateStateToUserNameError() {
        _loginState.update {
            it.copy(
                userNameError = "Username is required",
                onLoading = false
            )
        }
    }
    private fun updateStateToPasswordError() {
        _loginState.update {
            it.copy(
                passwordError = "Password is required",
                onLoading = false
            )
        }
    }
    private fun updateStateToSuccess() {
        _loginState.update {
            it.copy(
                userNameError = null,
                passwordError = null,
                onLoading = false
            )
        }
    }

    fun onUserNameChanged(userName: String) {
        _loginState.update { it.copy(userName = userName, userNameError = null) }
    }

    fun onPasswordChanged(password: String) {
        _loginState.update { it.copy(password = password, passwordError = null) }
    }
}
