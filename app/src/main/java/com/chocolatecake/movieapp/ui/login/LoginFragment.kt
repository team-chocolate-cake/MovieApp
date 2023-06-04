package com.chocolatecake.movieapp.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentLoginBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleKeyboardAppearanceEvent()
        handleFragmentEvents()
        showErrorMessageWhenErrorOccurs()
    }

    private fun handleKeyboardAppearanceEvent() {
        binding.edittextUsername.onFocusChangeListener = getOnEditTextFocusChangeClickListener()
        binding.edittextPassword.onFocusChangeListener = getOnEditTextFocusChangeClickListener()
    }

    private fun getOnEditTextFocusChangeClickListener() = OnFocusChangeListener { view, hasFocus ->
        binding.loginMotionLayout.setTransition(R.id.show_keyboard)
        binding.loginMotionLayout.setTransitionDuration(500)
        if (hasFocus) {
            binding.loginMotionLayout.transitionToEnd()
        }
    }

    private fun showErrorMessageWhenErrorOccurs() {
        lifecycleScope.launch {
            viewModel.loginState.collect {
                if (it.requestError) {
                    createSnackBar("Login Error :( ")
                }
            }
        }
    }

    private fun handleFragmentEvents() {
        lifecycleScope.launch { viewModel.loginEvent.collect { onEvent(it) } }
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