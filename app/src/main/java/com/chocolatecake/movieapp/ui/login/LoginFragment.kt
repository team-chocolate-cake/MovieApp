package com.chocolatecake.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentLoginBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch { viewModel.loginEvent.collect { onEvent(it) } }

        lifecycleScope.launch {
            viewModel.loginState.collect {
                if (it.requestError) {
                    createSnackBar("Login Error :( ")
                }
            }
        }
    }

    private fun onEvent(event: LoginUiEvent?) {
        when (event) {
            is LoginUiEvent.LoginEvent -> {
                // TODO --> Navigate To Home Screen
            }

            is LoginUiEvent.SignUpEvent -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }

            else -> {}
        }
    }
}