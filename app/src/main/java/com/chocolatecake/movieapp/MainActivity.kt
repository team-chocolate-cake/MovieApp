package com.chocolatecake.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.chocolatecake.movieapp.databinding.FragmentLoginBinding
import com.chocolatecake.movieapp.domain.usecases.auth.LoginUseCase
import com.chocolatecake.movieapp.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_login)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.buttonLogin.setOnClickListener {
            viewModel.login()
            Log.d("mimo", "------------MainActivity------->>> ")
        }
    }
}


