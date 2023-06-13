package com.chocolatecake.ui.search.utils

import androidx.databinding.BindingAdapter
import com.chocolatecake.viewmodel.search.SearchListener
import com.chocolatecake.viewmodel.search.SearchUiState
import com.google.android.material.chip.ChipGroup

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