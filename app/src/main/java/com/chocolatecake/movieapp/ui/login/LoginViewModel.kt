package com.chocolatecake.movieapp.ui.login

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.domain.usecases.auth.GetIsValidLoginUseCase
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import com.chocolatecake.movieapp.ui.base.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    resourceManager: ResourceManager
) : BaseViewModel(resourceManager) {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    private val _loginEvent = Channel<LoginUiEvent>()
    val loginEvent = _loginEvent.receiveAsFlow()

    fun onClickSignUp() {
        viewModelScope.launch {
            _loginEvent.send(LoginUiEvent.SignUpEvent)
        }
    }

    fun login() {
        viewModelScope.launch {
            val userName = _state.value.userName
            val password = _state.value.password
            _state.update { it.copy(isLoading = true) }
            when (loginUseCase(userName, password)) {
                LoginStateIndicator.USER_NAME_ERROR -> updateStateToUserNameError()
                LoginStateIndicator.PASSWORD_ERROR -> updateStateToPasswordError()
                LoginStateIndicator.REQUEST_ERROR -> updateStateToRequestError()
                LoginStateIndicator.SUCCESS -> updateStateToSuccessLogin()
            }
        }
    }

    private fun updateStateToRequestError() {
        _state.update {
            it.copy(
                requestError = true,
                isLoading = false
            )
        }
        _state.update {
            it.copy(
                requestError = false
            )
        }
    }

    private fun updateStateToUserNameError() {
        _state.update {
            it.copy(
                userNameError = resourceManager.getString(R.string.Username_is_required),
                isLoading = false
            )
        }
    }

    private fun updateStateToPasswordError() {
        _state.update {
            it.copy(
                passwordError =  resourceManager.getString(R.string.Password_is_required),
                isLoading = false
            )
        }
    }

    private fun updateStateToSuccessLogin() {
        _state.update {
            it.copy(userNameError = null, passwordError = null, isLoading = false)
        }
        viewModelScope.launch {
            _loginEvent.send(LoginUiEvent.LoginEvent(1))
        }
    }

    fun onUserNameChanged(userName: CharSequence) {
        _state.update { it.copy(userName = userName.toString(), userNameError = null) }
    }

    fun onPasswordChanged(password: CharSequence) {
        _state.update { it.copy(password = password.toString(), passwordError = null) }
    }

    override fun getData() {

    }
}


