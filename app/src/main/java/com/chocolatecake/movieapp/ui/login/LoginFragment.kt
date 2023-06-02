package com.chocolatecake.movieapp.ui.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentLoginBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()




}