package com.chocolatecake.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.auth.BuildConfig
import com.chocolatecake.ui.auth.R
import com.chocolatecake.ui.auth.databinding.FragmentLoginBinding
import com.chocolatecake.viewmodel.LoginUiEvent
import com.chocolatecake.viewmodel.LoginUiState
import com.chocolatecake.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginUiState, LoginUiEvent>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state by viewModel.state.collectAsState()
                LoginScreenContent(state, viewModel)
            }
        }
    }

    override fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.NavigateToHomeScreen -> {
                val navController = findNavController()
                navController.popBackStack(navController.graph.startDestinationId, false)
                navController.navigate(event.id)
            }

            is LoginUiEvent.SignUpEvent -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }

            is LoginUiEvent.ShowSnackBar -> {
                val keyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(view?.windowToken, 0)
                showSnackBar(event.message)
            }

        }
    }
}