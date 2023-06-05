package com.chocolatecake.movieapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentHomeBinding
import com.chocolatecake.movieapp.home.adapter.HomeAdapter
import com.chocolatecake.movieapp.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}