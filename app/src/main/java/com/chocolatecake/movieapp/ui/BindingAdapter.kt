package com.chocolatecake.movieapp.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.search.ui_state.SearchListener
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiState
import com.chocolatecake.movieapp.ui.util.createChip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.snackbar.Snackbar

@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .fitCenter()
        .centerCrop()
        .into(this)
}

@BindingAdapter(value = ["app:hideResult", "app:query"])
fun <T> View.hideResult(list: List<T>?, text: String) {
    if (list.isNullOrEmpty() || text.isNotBlank()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setRecyclerItems(items: List<T>?) {
    (adapter as BaseAdapter<T>).setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:loading"])
fun LinearProgressIndicator.isLoading(isLoading: Boolean?) {
    if (isLoading == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:setGenres", "app:listener", "app:chipSelected"])
fun ChipGroup.setGenres(
    items: List<SearchUiState.GenresMoviesUiState>?,
    listener: SearchListener,
    chipSelected: Int?
) {
    items?.let {
        it.forEach { genre -> this.addView(this.createChip(genre, listener)) }
    }

    val chipIndex = items?.indexOf(items.find { it.genreId == chipSelected }) ?: 0
    this.getChildAt(chipIndex)?.id?.let { this.check(it) }
}

@BindingAdapter("app:showError")
fun View.showErrorSnackBar(error: List<String>?) {
    error?.let {
        if (it.isNotEmpty()) {
            val errorMessage = it.last()
            Snackbar.make(this, errorMessage, Snackbar.LENGTH_SHORT).show()
        }
    }
}





