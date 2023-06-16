package com.chocolatecake.ui.watch_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentWatchHistoryBinding
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchHistoryFragment
    : BaseFragment<FragmentWatchHistoryBinding, WatchHistoryUiState, WatchHistoryUiEvent>() {

    override val layoutIdFragment = R.layout.fragment_watch_history
    override val viewModel by viewModels<WatchHistoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupAdapter() {
        val adapter = WatchHistoryAdapter(mutableListOf(), viewModel)
        binding.watchHistoryRecyclerView.adapter = adapter
    }


    override fun onEvent(event: WatchHistoryUiEvent) {

    }
}