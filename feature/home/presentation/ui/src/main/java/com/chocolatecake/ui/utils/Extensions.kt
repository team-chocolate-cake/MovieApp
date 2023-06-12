package com.chocolatecake.ui.utils

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.chocolatecake.ui.R
import com.chocolatecake.ui.databinding.SearchChipsFilterItemBinding
import com.chocolatecake.viewmodel.search.SearchListener
import com.chocolatecake.viewmodel.search.SearchUiState
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

@BindingAdapter(value = ["app:setGenres", "app:listener", "app:chipSelected"])
fun ChipGroup.setGenres(
    items: List<SearchUiState.GenresMoviesUiState>?,
    listener: SearchListener,
    chipSelected: Int?
) {
    this.removeAllViews()
    items?.let {
        it.forEach { genre -> this.addView(this.createChip(genre, listener)) }
    }

    val chipIndex = items?.indexOf(items.find { it.genreId == chipSelected }) ?: 0
    this.getChildAt(chipIndex)?.id?.let { this.check(it) }
}