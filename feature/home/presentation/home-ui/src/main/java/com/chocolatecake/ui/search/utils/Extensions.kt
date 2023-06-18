package com.chocolatecake.ui.search.utils

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.SearchChipsFilterItemBinding
import com.chocolatecake.ui.home.databinding.TvChipFilterItemBinding
import com.chocolatecake.viewmodel.search.SearchListener
import com.chocolatecake.viewmodel.search.SearchUiState
import com.chocolatecake.viewmodel.tv_shows.GenresTVShowsUiState
import com.chocolatecake.viewmodel.tv_shows.TVShowsListener
import com.google.android.material.chip.ChipGroup

fun ChipGroup.createChip(item: SearchUiState.GenresMoviesUiState, listener: SearchListener): View {
    val binding: SearchChipsFilterItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_chips_filter_item,
        this,
        false
    )
    binding.item = item
    binding.listener = listener
    return binding.root
}

fun ChipGroup.createTVChip(item: GenresTVShowsUiState, listener: TVShowsListener): View {
    val binding: TvChipFilterItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_chips_filter_item,
        this,
        false
    )
    binding.item = item
    binding.listener = listener
    return binding.root
}
