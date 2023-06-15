package com.chocolatecake.ui.movieDetails

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup

@BindingAdapter(value = ["app:imageUrlByBackDropPath"])
fun ImageView.loadImageByBackDropPath(backDropPath: String?) {
    //TODO Remove hardcoded link
    if (!backDropPath.isNullOrEmpty())
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500"+backDropPath)
            .fitCenter()
            .centerCrop()
            .into(this)
}

@BindingAdapter(value = ["app:Genres"])
fun ChipGroup.setGenresMovieDetails(
    items: List<String>?,
) {
    this.removeAllViews()
    items?.forEach { genre ->
        var chip = Chip(this.context)
        chip.setChipBackgroundColorResource(com.chocolatecake.bases.R.color.on_background_38);
        chip.setCloseIconVisible(true)
        chip.setTextColor(getResources().getColor(com.chocolatecake.bases.R.color.on_background_38))
        chip.setText(genre)
        chip.ensureAccessibleTouchTarget(0)
        val chipDrawable = ChipDrawable.createFromAttributes(
            this.context,
            null,
            0,
            com.chocolatecake.bases.R.style.MovieDetailsChipStyle
        )
        chip.setChipDrawable(chipDrawable)
        this.addView(chip)
    }
}