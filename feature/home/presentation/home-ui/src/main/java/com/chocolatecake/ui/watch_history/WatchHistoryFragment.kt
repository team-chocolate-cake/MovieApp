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
import com.chocolatecake.ui.common.base.SwipeToDeleteItem
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
    private lateinit var adapter: WatchHistoryAdapter
    private val deletionIndicatorSnackBar by lazy {
        setupSnackBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerAdapter()
        swipeToDeleteItemSetup(binding.watchHistoryRecyclerView)
    }

    private fun setupRecyclerAdapter() {
        adapter = WatchHistoryAdapter(mutableListOf(), viewModel)
        binding.watchHistoryRecyclerView.adapter = adapter
    }


    override fun onEvent(event: WatchHistoryUiEvent) {
        when (event) {
            is WatchHistoryUiEvent.NavigateToMovieDetails -> navigateToMovieDetails(event.movieId)
            is WatchHistoryUiEvent.ShowDeleteSnackBar -> deletionIndicatorSnackBar.show()
            is WatchHistoryUiEvent.Error -> showSnackBar(getString(R.string.cannot_fetch_movies))
            is WatchHistoryUiEvent.OnClickBack -> onBackButtonPressed()
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController()
            .navigate(
                WatchHistoryFragmentDirections
                    .actionWatchHistoryFragmentToMovieDetailsFragment(movieId)
            )
    }

    private fun swipeToDeleteItemSetup(itemRv: RecyclerView) {
        val swipeGesture = swipeGestureAnonymousObject()
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(itemRv)
    }

    private fun swipeGestureAnonymousObject() = object : SwipeToDeleteItem() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            try {
                finishNotCompletedDeletion()
                handleSwipes(direction, viewHolder.absoluteAdapterPosition)
            } catch (e: Exception) { }
        }
    }

    private fun finishNotCompletedDeletion() {
        viewModel.deleteItemFromDataBase()
        viewModel.initTheDeletionStates()
    }

    private fun handleSwipes(direction: Int, position: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> {
                onSwipeLeftActions(position)
            }
        }
    }

    private fun onSwipeLeftActions(position: Int) {
        viewModel.setPosition(position)
        viewModel.deleteItemFromUi()
    }

    private fun setupSnackBar(): Snackbar {
        return Snackbar.make(
            binding.root,
            getString(com.chocolatecake.bases.R.string.item_deleted),
            Snackbar.LENGTH_LONG
        )
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
            .setActionTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.chocolatecake.bases.R.color.orangeRed
                )
            )
            .addCallback(setupSnackBarCallback())
            .setAction(getString(com.chocolatecake.bases.R.string.undo)) {
                viewModel.addItemToUi()
            }
    }

    private fun setupSnackBarCallback() = object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(
            transientBottomBar: Snackbar?,
            event: Int
        ) {
            viewModel.deleteItemFromDataBase()
            super.onDismissed(transientBottomBar, event)
        }

        override fun onShown(snackBar: Snackbar?) {
            viewModel.onSnackBarShown()
            super.onShown(snackBar)
        }
    }

    private fun createToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    fun onBackButtonPressed() {
        findNavController().popBackStack()
    }

    override fun onStop() {
        super.onStop()
        viewModel.deleteItemFromDataBase()
    }
}