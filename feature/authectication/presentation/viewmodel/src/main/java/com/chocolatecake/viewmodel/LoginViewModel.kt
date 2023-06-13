package com.chocolatecake.viewmodel

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.NavigationRes
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.usecase.LoginUseCase
import com.chocolatecake.usecase.model.LoginStateIndicator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val stringsRes: StringsRes,
    private val navigationRes: NavigationRes,
) : BaseViewModel<LoginUiState, LoginUiEvent>() {

    override fun initialState() = LoginUiState()

    fun onClickSignUp() {
        sendEvent(LoginUiEvent.SignUpEvent)
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
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun updateStateToRequestError() {
        sendEvent(LoginUiEvent.ShowSnackBar(stringsRes.theRequestFailed))
    }

    private fun updateStateToUserNameError() {
        _state.update { it.copy(userNameError = stringsRes.usernameIsRequired) }
    }

    private fun updateStateToPasswordError() {
        _state.update { it.copy(passwordError = stringsRes.passwordIsRequired) }
    }

    private fun updateStateToSuccessLogin() {
        _state.update { it.copy(userNameError = null, passwordError = null, isLoading = false) }
        sendEvent(LoginUiEvent.NavigateToHomeScreen(navigationRes.homeFeature))
    }

    override fun getData() {

    }
}

