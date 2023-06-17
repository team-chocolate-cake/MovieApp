package com.chocolatecake.ui.search.utils

import android.view.View
import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.search.SearchListener
import com.chocolatecake.viewmodel.search.SearchUiState
import com.chocolatecake.viewmodel.tv_shows.GenresTVShowsUiState
import com.chocolatecake.viewmodel.tv_shows.TVShowsListener
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

@BindingAdapter(value = ["app:setTVGenres", "app:listener", "app:chipSelected"])
fun ChipGroup.setTVGenres(
    items: List<GenresTVShowsUiState>?,
    listener: TVShowsListener,
    chipSelected: Int?
) {
    this.removeAllViews()
    items?.let {
        it.forEach { genre -> this.addView(this.createTVChip(genre, listener)) }
    }

    val chipIndex = items?.indexOf(items.find { it.genreId == chipSelected }) ?: 0
    this.getChildAt(chipIndex)?.id?.let { this.check(it) }
}

@BindingAdapter(value = ["app:selectedMedia"])
fun ChipGroup.setSelectedMedia(media: SearchUiState.SearchMedia) {
    when (media) {
        SearchUiState.SearchMedia.MOVIE -> check(R.id.chipMovie)
        SearchUiState.SearchMedia.TV -> check(R.id.chipTV)
        SearchUiState.SearchMedia.PEOPLE -> check(R.id.chipPerson)
    }
}

@BindingAdapter(value = ["app:hideImageButton"])
fun ImageButton.setHideImageButton(hide: Boolean?) {
    this.visibility = if (hide == true) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["app:searchLayoutManager"])
fun RecyclerView.setSearchLayoutManager(searchUiState: SearchUiState?) {
    val layoutManager = when (searchUiState?.mediaType) {
        SearchUiState.SearchMedia.MOVIE, SearchUiState.SearchMedia.TV -> {
            LinearLayoutManager(context)
        }
        SearchUiState.SearchMedia.PEOPLE -> {
            GridLayoutManager(context, 3)
        }
        else -> {
            LinearLayoutManager(context)
        }
    }
    this.layoutManager = layoutManager
}