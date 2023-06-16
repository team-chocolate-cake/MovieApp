package com.chocolatecake.ui.watch_history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentWatchHistoryBinding
import com.chocolatecake.viewmodel.watch_history.WatchHistoryViewModel
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.Date
import kotlin.math.abs

@AndroidEntryPoint
class WatchHistoryFragment
    : BaseFragment<FragmentWatchHistoryBinding, WatchHistoryUiState, WatchHistoryUiEvent>() {

    override val layoutIdFragment = R.layout.fragment_watch_history
    override val viewModel by viewModels<WatchHistoryViewModel>()
    private val itemsCreator = WatchHistoryRecyclerItemsCreator()

    private lateinit var adapter: WatchHistoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        collectMovies()
    }

    private fun collectMovies() {
        collectLatest {
            viewModel.state.collect {
                adapter.setItems(
                    itemsCreator.createItems(it.movies)
                )
            }
        }
    }

    private fun setupAdapter() {
        adapter = WatchHistoryAdapter(mutableListOf(), viewModel)
        binding.watchHistoryRecyclerView.adapter = adapter
    }

    override fun onEvent(event: WatchHistoryUiEvent) {

    }

}