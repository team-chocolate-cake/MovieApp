package com.chocolatecake.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentHomeBinding
import com.chocolatecake.movieapp.ui.home.adapter.HomeAdapter
import com.chocolatecake.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        collectAdapterData()
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter(mutableListOf(), viewModel)
        binding.recyclerViewHome.adapter = homeAdapter
    }

    private fun collectAdapterData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                homeAdapter.setItems(
                    mutableListOf(
                        it.upComingMovies,
                        it.nowPlayingMovies,
                        it.trendingMovies,
                        it.topRated,
                        it.popularPeople,
                        it.popularMovies,
                        it.recommended
                    )
                )
                Log.d("mimo", it.toString())
            }
        }
    }
}