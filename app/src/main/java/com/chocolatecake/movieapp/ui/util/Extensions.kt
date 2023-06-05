package com.chocolatecake.movieapp.ui.util

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.SearchChipsFilterItemBinding
import com.chocolatecake.movieapp.ui.search.ui_state.SearchListener
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiState
import com.google.android.material.chip.ChipGroup

fun ChipGroup.createChip(item: SearchUiState.GenresMoviesUiState, listener: SearchListener): View {
    val binding: SearchChipsFilterItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.search_chips_filter_item, this, false
    )
    binding.item = item
    binding.listener = listener
    return binding.root
}