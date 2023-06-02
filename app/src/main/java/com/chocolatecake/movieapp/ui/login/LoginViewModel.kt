package com.chocolatecake.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.domain.usecases.auth.GetIsValidLoginUseCase
import com.chocolatecake.movieapp.domain.usecases.auth.LoginStateIndicator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val getIsValidLoginUseCase: GetIsValidLoginUseCase) :
    ViewModel() {
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    private val _loginEvent = Channel<LoginUiEvent?>()
    val loginEvent = _loginEvent.receiveAsFlow()
    fun onClickSignUp() {
        viewModelScope.launch {
            _loginEvent.send(LoginUiEvent.SignUpEvent)
        }
    }

    fun login() {
        val userName = _loginState.value.userName
        val password = _loginState.value.password

        viewModelScope.launch {
            _loginState.update { it.copy(isLoading = true) }
            when (getIsValidLoginUseCase(userName, password)) {
                LoginStateIndicator.USER_NAME_ERROR -> updateStateToUserNameError()
                LoginStateIndicator.PASSWORD_NAME_ERROR -> updateStateToPasswordError()
                LoginStateIndicator.REQUEST_ERROR -> updateStateToRequestError()
                LoginStateIndicator.SUCCESS -> updateStateToSuccessLogin()
            }
        }
    }

    private fun updateStateToRequestError() {
        _loginState.update {
            it.copy(
                requestError = true,
                isLoading = false
            )
        }
    }

    private fun updateStateToUserNameError() {
        _loginState.update {
            it.copy(
                userNameError = "Username is required",
                isLoading = false
            )
        }
    }

    private fun updateStateToPasswordError() {
        _loginState.update {
            it.copy(
                passwordError = "Password is required",
                isLoading = false
            )
        }
    }

    private fun updateStateToSuccessLogin() {
        _loginState.update {
            it.copy(userNameError = null, passwordError = null, isLoading = false)
        }
        viewModelScope.launch {
            _loginEvent.send(LoginUiEvent.LoginEvent(1))
        }
    }

    fun onUserNameChanged(userName: CharSequence) {
        _loginState.update { it.copy(userName = userName.toString(), userNameError = null) }
        Log.d("mimo", userName.toString())
    }

    fun onPasswordChanged(password: CharSequence) {
        _loginState.update { it.copy(password = password.toString(), passwordError = null) }
        Log.d("mimo", password.toString())
    }
}
