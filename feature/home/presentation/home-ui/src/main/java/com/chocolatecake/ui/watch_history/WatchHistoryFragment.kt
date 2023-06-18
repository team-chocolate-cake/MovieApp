package com.chocolatecake.ui.watch_history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentWatchHistoryBinding
import com.chocolatecake.viewmodel.watch_history.WatchHistoryViewModel
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

import dagger.hilt.android.AndroidEntryPoint

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
        swipeToGestureSetup(binding.watchHistoryRecyclerView)
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
        when(event){
            is WatchHistoryUiEvent.NavigateToMovieDetails -> navigateToMovieDetails(event.movieId)
            is WatchHistoryUiEvent.ShowSnackBar -> showSnackBar(event.message)
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
       findNavController()
           .navigate(WatchHistoryFragmentDirections
               .actionWatchHistoryFragmentToMovieDetailsFragment(movieId)
           )
    }

    private fun swipeToGestureSetup(itemRv: RecyclerView?) {
        val swipeGesture = swipeGestureAnonymousObject()
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(itemRv)
    }

    private fun swipeGestureAnonymousObject() = object : SwipeGesture() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            try {
                handleSwipeAction(direction, viewHolder.absoluteAdapterPosition)
            } catch (e: Exception) {
                createToast(e.message.toString())
            }
        }
    }

    private fun handleSwipeAction(direction: Int, position: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> {
                deleteItemFromUi(position)
                setupDeleteSnackBar(position)
            }
        }
    }


    private var isUndo = false
    private fun setupDeleteSnackBar(position: Int) {
        val snackBar = Snackbar.make(
            binding.root,
            getString(com.chocolatecake.bases.R.string.item_deleted),
            Snackbar.LENGTH_LONG
        )

        snackBar.addCallback(getCallback(position))
        snackBar.animationMode = Snackbar.ANIMATION_MODE_FADE
        snackBar.setActionTextColor(
            ContextCompat.getColor(
                requireContext(),
                com.chocolatecake.bases.R.color.orangeRed
            )
        )
        snackBar.show()
    }

    private fun getCallback(
        position: Int
    ) = object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(
            transientBottomBar: Snackbar?,
            event: Int
        ) {
            if (!isUndo) {
                deleteItemFromDataBase()
            }
            super.onDismissed(transientBottomBar, event)
        }

        override fun onShown(transientBottomBar: Snackbar?) {
            isUndo = false
            transientBottomBar?.setAction(getString(com.chocolatecake.bases.R.string.undo)) {
                isUndo = true
                addItemToUI(position)
            }
            super.onShown(transientBottomBar)
        }
    }

    private fun deleteItemFromDataBase() {
        viewModel.deleteItemFromDataBase()
    }

    private fun deleteItemFromUi(position: Int) {
        viewModel.deleteItemFromUi(position - 1)
    }

    private fun addItemToUI(position: Int) {
        viewModel.addItemToUI(position - 1)
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}